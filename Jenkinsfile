pipeline {
    agent any
 
    triggers {
        pollSCM('H/5 * * * *')
    }
    
    environment {
        DMALL_DOCKER_REGISTRY='ec2-52-80-144-157.cn-north-1.compute.amazonaws.com.cn:5000'
        DEV_RANCHER_SERVER='http://ec2-54-222-228-139.cn-north-1.compute.amazonaws.com.cn:8080/v2-beta/projects/1a7'
    }
    
    stages {
        stage('repo clean up'){
            steps {
                step([$class: 'WsCleanup'])
            }
        }

        stage('Checkout') {
            steps {
                step([$class: 'WsCleanup'])
            git  poll: true,  credentialsId: 'git-viewer', url: 'git@gitee.com:tws-micro-service/dmall-order-service.git', branch: 'master'
            }
                
        }

        stage('Build') {
            steps{
                sh './gradlew clean build'
            }
        }

        stage('Check') {
            parallel {
                stage('Findbugs') {
                        agent none
                        steps {
                            echo 'Findbugs is finished.'
                        }
                    }
                stage('Checkstyle') {
                        agent none
                        steps {
                            echo 'Checkstyle is finished.'
                        }
                    }
                stage('PMD') {
                        agent none
                        steps {
                            echo 'PMD is finished.'
                        }
                    }
            }
        }

        stage('Test') {
            steps{
                sh 'SPRING_PROFILES_ACTIVE=test ./gradlew test'
            }
        }

        stage('Docker image') {
            steps{
                sh './genImages.sh'
            }
        }

        stage('Deploy to DEV') {
            steps{
                withCredentials([usernamePassword(credentialsId: 'dev_rencher_api_key', passwordVariable: 'SECRET', usernameVariable: 'KEY')]) {
                    sh './deployToDEV.sh'
                }
            }
        }
  }  
}