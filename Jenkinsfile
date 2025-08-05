pipeline {
    agent any  // Jenkins agent sử dụng để thực hiện các bước

    environment {
        // Cấu hình Docker Hub Credentials (ID bạn đã tạo)
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'
        IMAGE_NAME = 'nigasante/newspaper-app'  // Tên image trên Docker Hub
        DOCKER_TAG = 'latest'                  // Tag của image
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout mã nguồn từ GitHub repository của bạn
                git branch: 'main', url: 'https://github.com/nigasante/niga2.git'
            }
        }

        stage('Build JAR') {
            steps {
                // Build ứng dụng Spring Boot thành file JAR
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image từ Dockerfile
                    docker.build("${IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    // Đăng nhập vào Docker Hub để push image
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        // Đăng nhập với Docker Hub credentials
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Đẩy Docker image lên Docker Hub với retry và timeout
                    timeout(time: 5, unit: 'MINUTES') { // Giới hạn thời gian push là 5 phút
                        docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                            retry(3) { // Thử lại 3 lần nếu lỗi
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
                    // Dừng và xóa container cũ nếu tồn tại
                    sh '''
                    docker ps -a --filter name=newspaper-app --format '{{.Names}}' | grep -q newspaper-app
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
                    // Chạy container mới với các biến môi trường
                    sh 'docker run -d --name newspaper-app -p 8081:8081 --network host -e SPRING_DATASOURCE_URL=jdbc:sqlserver://host.docker.internal:1433;databaseName=NewspaperAppDB;encrypt=false;trustServerCertificate=true -e SPRING_DATASOURCE_USERNAME=sa -e SPRING_DATASOURCE_PASSWORD=1234 ${IMAGE_NAME}:${DOCKER_TAG}'
                }
            }
        }

        stage('Cleanup') {
            steps {
                // Dọn dẹp image Docker không cần thiết sau khi push
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