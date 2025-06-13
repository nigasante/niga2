pipeline {
 agent any
 
 stages {
	stage('clone'){
		steps {
			echo 'Cloning source code'
			git branch:'main', url: 'https://github.com/nigasante/niga2.git'
		}
	}
	stage('restore package') {
		steps
		{
			echo 'Restore package'
			bat 'dotnet restore'
		}
	}
	stage ('build') {
		steps {
			echo 'build project netcore'
			bat 'dotnet build  --configuration Release'
		}
	}
	stage ('tests') {
		steps{
			echo 'running test...'
			bat 'dotnet test --no-build --verbosity normal'
		}
	}
	stage ('public den t thu muc')
	{
		steps{
			echo 'Publishing...'
			bat 'dotnet publish -c Release -o ./publish'
		}
	}

	stage ('Stop iis') {
		steps {
			echo 'Stop iis'
			bat 'iisreset /stop' // stop iis de ghi de file 
		}
	}
	stage ('Publish') {
		steps {
			echo 'public 2 runnig folder'
			bat 'xcopy "%WORKSPACE%\\publish" /E /Y /I /R "c:\\test1-netcore2"'
			// /E: copy cả thư mục con, kể cả rỗng. /Y: không hỏi xác nhận ghi đè. ; /Q: yên lặng, không in tên file.; /R: ghi đè cả file chỉ đọc (nếu có).; I khong co folder thi tao moi
		}
	}
	stage ('start IIS') {
		steps {
			echo 'Start IIS'
			bat 'iisreset /start' // start iis sau khi ghi de file
		}
	}

	stage('Deploy to IIS') {
            steps {
                powershell '''
               
                # Tạo website nếu chưa có
                Import-Module WebAdministration
                if (-not (Test-Path IIS:\\Sites\\MySite2)) {
                    New-Website -Name "MySite2" -Port 82 -PhysicalPath "c:\\test1-netcore2"
                }
                '''
            }
        } // end deploy iis

	// stage('Open Website') {
 	//    steps {
    //     	//bat 'start http://localhost:81'
    // 	}
	//}
  } // end stages
}//end pipeline