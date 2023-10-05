pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                mvn compile
                mvn package
            }
        }
    }
}
