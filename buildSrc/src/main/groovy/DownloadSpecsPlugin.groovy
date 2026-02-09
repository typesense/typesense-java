import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Downloads Typesense API spec from https://github.com/typesense/typesense-api-spec/blob/master/openapi.yml
 */
class DownloadSpecsPlugin implements Plugin<Project> {

    def specUrl = 'https://raw.githubusercontent.com/typesense/typesense-api-spec/master/openapi.yml'

    @Override
    void apply(Project project) {
        project.tasks.register("downloadApiSpec") {
            doLast {
                println('Downloading spec')
                File file = new File("${project.buildDir}/openapi.yml")
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists())
                    file.createNewFile();
                file.withOutputStream { out ->
                    new URL(specUrl).withInputStream { from -> out << from }
                }
                
                // Patch the OpenAPI spec to fix SynonymItemSchema allOf issue
                // The swagger code generator doesn't handle allOf correctly, so we convert it
                // to a direct definition that includes all fields
                println('Patching OpenAPI spec for SynonymItemSchema')
                String content = file.text
                
                // Find and replace the allOf structure with a direct definition
                String allOfBlock = '''    SynonymItemSchema:
      allOf:
        - type: object
          required:
            - id
          properties:
            id:
              type: string
              description: Unique identifier for the synonym item
        - $ref: "#/components/schemas/SynonymItemUpsertSchema"'''
        
                String replacement = '''    SynonymItemSchema:
      type: object
      required:
        - id
        - synonyms
      properties:
        id:
          type: string
          description: Unique identifier for the synonym item
        synonyms:
          type: array
          description: Array of words that should be considered as synonyms
          items:
            type: string
        root:
          type: string
          description: For 1-way synonyms, indicates the root word that words in the synonyms parameter map to
        locale:
          type: string
          description: Locale for the synonym, leave blank to use the standard tokenizer
        symbols_to_index:
          type: array
          description: By default, special characters are dropped from synonyms. Use this attribute to specify which special characters should be indexed as is
          items:
            type: string'''
        
                if (content.contains(allOfBlock)) {
                    content = content.replace(allOfBlock, replacement)
                    println('Successfully patched SynonymItemSchema')
                } else {
                    println('Warning: SynonymItemSchema allOf block not found, skipping patch')
                }

                // Patch: Add rerank_hybrid_matches to SearchParameters
                // The server accepts this parameter for all search endpoints (single and multi),
                // but the spec only defines it on MultiSearchCollectionParameters.
                println('Patching OpenAPI spec: adding rerank_hybrid_matches to SearchParameters')
                String searchParamsAnchor = '''\
        conversation_id:
          description: >
            The Id of a previous conversation to continue, this tells Typesense to include prior context when communicating with the LLM.
          type: string

    MultiSearchParameters:'''

                String searchParamsReplacement = '''\
        conversation_id:
          description: >
            The Id of a previous conversation to continue, this tells Typesense to include prior context when communicating with the LLM.
          type: string
        rerank_hybrid_matches:
          type: boolean
          description: >
            When true, computes both text match and vector distance scores for all matches in hybrid search.
            Documents found only through keyword search will get a vector distance score, and
            documents found only through vector search will get a text match score.
          default: false

    MultiSearchParameters:'''

                if (content.contains(searchParamsAnchor)) {
                    content = content.replace(searchParamsAnchor, searchParamsReplacement)
                    println('Successfully added rerank_hybrid_matches to SearchParameters')
                } else {
                    println('Warning: SearchParameters anchor not found, skipping rerank_hybrid_matches patch')
                }

                file.text = content
            }
        }
    }
}
