pipeline {
    agent any

    stages {
        stage('Source') {
            steps {
                git branch: 'dev', credentialsId: 'github-user-jenkins-credentials', url: 'https://github.com/bugdebugging/danggeun'
                sh 'chmod 777 ./mvnw'
            }
        }
        stage('Build') {
            steps{
                sh 'sudo ./mvnw package'
            }
        }
        
        stage('Test') {
            steps{
                sh 'sudo ./mvnw test'
            }
        }
        stage('docker build') {
            steps {
                docker.build("isvara/danggeun:latest")
            }
        }
    }
}
