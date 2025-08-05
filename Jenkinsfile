pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'  // ID của credential Docker Hub
        IMAGE_NAME = 'nigasante/newspaper-app'             // Tên repository trên Docker Hub
        DOCKER_TAG = 'latest'                          // Tag của image
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/nigasante/niga2.git'
            }
        }
        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }
        stage('Login to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        // Đăng nhập vào Docker Hub
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        docker.image("${IMAGE_NAME}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
        stage('Stop and Remove Old Container') {
            steps {
                script {
                    sh '''
                    docker ps -a --filter "name=newspaper-app" --format "{{.Names}}" | grep -q newspaper-app
                    if [ $? -eq 0 ]; then
                        docker stop newspaper-app || true
                        docker rm newspaper-app || true
                    fi
                    '''
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    sh 'docker run -d --name newspaper-app -p 8181:8081 --network host -e SPRING_DATASOURCE_URL=jdbc:sqlserver://host.docker.internal:1433;databaseName=NewspaperAppDB;encrypt=false;trustServerCertificate=true -e SPRING_DATASOURCE_USERNAME=sa -e SPRING_DATASOURCE_PASSWORD=L@mkhoa123 ${IMAGE_NAME}:${DOCKER_TAG}'
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully! Application is running at http://localhost:8081'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}