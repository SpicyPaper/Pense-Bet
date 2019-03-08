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
        stage('IntegrationTest'){
			steps {
				unstash "app"
				sh 'java -jar ./Pense-Bet/target/Pense-Bet-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &' 
				sh 'sleep 30'
				sh 'chmod +x ./runTest.sh'
				sh './runTest.sh'

				cleanWs()

				}
            
        }
        stage('Deploy') {
            steps {
                echo 'Deploying'
            }
        }
    }
}