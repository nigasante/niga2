pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code...'
                git branch: 'main', url: 'https://github.com/nigasante/niga2.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java project with Maven...'
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application...'
                bat 'mvn package'
            }
        }

        stage('Deploy to Folder') {
            steps {
                echo 'Deploying build artifact to IIS folder...'
                // Update path below based on your WAR/JAR location
                bat 'xcopy "target\\*.jar" /Y /I "C:\\wwwroot\\myproject"'
            }
        }

        stage('Deploy to IIS') {
            steps {
                powershell '''
                Import-Module WebAdministration
                if (-not (Test-Path IIS:\\Sites\\MyJavaApp)) {
                    New-Website -Name "MyJavaApp" -Port 85 -PhysicalPath "C:\\wwwroot\\myproject"
                }
                '''
            }
        }
    }
}
