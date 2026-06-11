{
  description = "Typesense Java client dev shell";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config.permittedInsecurePackages = [ "gradle-7.6.6" ];
        };
      in {
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            jdk17
            gradle_7
            jdt-language-server
          ];

          JDK8_HOME = "${pkgs.jdk8}";

          shellHook = ''
            export JAVA_HOME=${pkgs.jdk17}
            echo "Java (jdtls/build runtime):"
            java -version
            echo ""
            echo "Project JDK 8 available at: $JDK8_HOME"
            echo ""
            echo "Gradle:"
            gradle --version | grep '^Gradle'
            echo ""
            echo "jdtls: $(command -v jdtls)"
          '';
        };
      });
}
