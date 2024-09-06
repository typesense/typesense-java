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
            println('Downloading spec')
            try {
                File file = new File("${project.buildDir}/openapi.yml")
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists())
                    file.createNewFile();
                file.withOutputStream { out ->
                    new URL(specUrl).withInputStream { from -> out << from }
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
