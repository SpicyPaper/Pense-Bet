pipeline {
    agent any
    stages {
        stage('Build') {
			steps {
				echo 'Building'
				mvn sonar:sonar   
				-Dsonar.projectKey=pense-bet   
				-Dsonar.organization=kurokabe-github   
				-Dsonar.host.url=https://sonarcloud.io   
				-Dsonar.login=6f674b6254665e4ab884cf6c2998b3cfa7d5c1c1
			}
        }
        stage('Test') {
            steps {
                echo 'Testing'
            }
		}
        stage('Deploy') {
            steps {
                echo 'Deploying'
            }
        }
    }
}