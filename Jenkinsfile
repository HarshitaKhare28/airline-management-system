pipeline {
    agent none
    stages {
        stage('Build and Test') {
            agent {
                docker {
                    image 'maven:3.8.1-jdk-11'
                    args '-v %USERPROFILE%\\.m2:/root/.m2'
                }
            }
            steps {
                checkout scm
                sh 'mvn clean package'
            }
        }
        stage('Deploy to EC2') {
            agent any
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh '''
                        scp -o StrictHostKeyChecking=no target/airline-management-system.jar ubuntu@13.234.114.175:/opt/airline-app/
                        ssh -o StrictHostKeyChecking=no ubuntu@13.234.114.175 "pkill -f 'java -jar /opt/airline-app/airline-management-system.jar' || true; nohup java -jar /opt/airline-app/airline-management-system.jar > /opt/airline-app/app.log 2>&1 &"
                    '''
                }
            }
        }
    }
}