pipeline {
    agent any
    stages {
        stage('Build') {
			agent {
			  docker {
			   image 'maven:3-alpine'
			  }
			}
			steps {
				sh '(cd ./Pense-Bet/; mvn clean package)'
				stash name: "app", includes: "**"
			}
        }
        stage('IntegrationTest'){
			agent{ 
			docker{
				image 'lucienmoor/katalon-for-jenkins:latest'
				args '-p 8888:8080'
			}
		}
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