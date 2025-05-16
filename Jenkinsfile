pipeline {
    agent any
    
    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'API tests passed successfully!'
        }
        failure {
            echo 'API tests failed. Please check the logs for details.'
        }
    }
} 