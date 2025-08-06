pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'
        IMAGE_NAME = 'nigasante/newspaper-app'
        DOCKER_TAG = 'latest'
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
                        // Login successful
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                            retry(3) {
                                docker.image("${IMAGE_NAME}:${DOCKER_TAG}").push()
                            }
                        }
                    }
                }
            }
        }

        stage('Stop and Remove Old Container') {
            steps {
                script {
                    sh '''
                    container=$(docker ps -a --filter name=newspaper-app --format '{{.Names}}')
                    if [ ! -z "$container" ]; then
                        echo "Stopping and removing existing container: $container"
                        docker stop $container || true
                        docker rm $container || true
                    else
                        echo "No existing container found."
                    fi
                    '''
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    sh '''
                    docker run -d --name newspaper-app -p 8181:8081 --network host \
                    -e SPRING_DATASOURCE_URL="jdbc:sqlserver://host.docker.internal:1433;databaseName=NewspaperAppDB;encrypt=false;trustServerCertificate=true" \
                    -e SPRING_DATASOURCE_USERNAME=sa \
                    -e SPRING_DATASOURCE_PASSWORD=1234 \
                    ${IMAGE_NAME}:${DOCKER_TAG}
                    '''
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh 'docker rmi ${IMAGE_NAME}:${DOCKER_TAG} || true'
            }
        }
    }

    post {
        success {
            echo 'Docker image đã được đẩy lên Docker Hub và ứng dụng đang chạy tại http://localhost:8081!'
        }
        failure {
            echo 'Pipeline thất bại!'
        }
    }
}
