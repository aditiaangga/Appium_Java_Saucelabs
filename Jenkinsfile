pipeline {
    agent {
        label 'master'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean test -Dtest=%runner%'
            }

           post {
                always {
                    archiveArtifacts artifacts: 'target/cucumber-result/**', fingerprint: true
                    cucumber (
                        buildStatus: 'UNSTABLE',
                        reportTitle: 'Cucumber Report',
                        fileIncludePattern: 'target/cucumber-result/*/*.json',
                        mergeFeaturesWithRetest: true,
                        skipEmptyJSONFiles: true
                    )
                }
            }
        }
    }
}