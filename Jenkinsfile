pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'  // ID của Docker Hub credential trong Jenkins
        IMAGE_NAME = 'nigasante/newspaper-app'           // Docker Hub repo
        DOCKER_TAG = 'latest'                            // Docker image tag
        CONTAINER_NAME = 'newspaper-app'                 // Docker container name
        EXPOSED_PORT = '8081'                            // Port ứng dụng chạy bên trong container
        HOST_PORT = '8181'                               // Port sẽ mở ra trên máy host
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
                        echo 'Logged into Docker Hub'
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
                    def containerExists = sh(
                        script: "docker ps -a --filter name=${CONTAINER_NAME} --format '{{.Names}}' | grep -w ${CONTAINER_NAME}",
                        returnStatus: true
                    ) == 0

                    if (containerExists) {
                        echo "Stopping and removing old container: ${CONTAINER_NAME}"
                        sh "docker stop ${CONTAINER_NAME} || true"
                        sh "docker rm ${CONTAINER_NAME} || true"
                    } else {
                        echo "No old container found."
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    sh """
                    docker run -d \
                        --name ${CONTAINER_NAME} \
                        -p ${HOST_PORT}:${EXPOSED_PORT} \
                        --network host \
                        -e SPRING_DATASOURCE_URL=jdbc:sqlserver://host.docker.internal:1433;databaseName=NewspaperAppDB;encrypt=false;trustServerCertificate=true \
                        -e SPRING_DATASOURCE_USERNAME=sa \
                        -e SPRING_DATASOURCE_PASSWORD=L@mkhoa123 \
                        ${IMAGE_NAME}:${DOCKER_TAG}
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully! Application is running at http://localhost:${HOST_PORT}"
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}
