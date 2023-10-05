pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
    post {
        success {
            echo 'Start to push release build to github'
            archiveArtifacts artifacts: 'target/crypto-trading-system-0.0.1-SNAPSHOT.jar'
        }
    }
}
