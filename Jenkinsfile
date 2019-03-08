pipeline {
    agent any
    stages {
        stage('Build') {
			steps {
				sh '(cd ./Pense-Bet/; mvn clean package)'
				stash name: "app", includes: "**"
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