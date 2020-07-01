#!/usr/bin/env groovy
pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Publish') {
            steps {
                sh 'docker build -f Dockerfile -t guastaferri/music-albums:1.0.1 .'
                withDockerRegistry([ credentialsId: "695a151f-0c58-4927-b87c-0e78014c525b", url: "" ]) {
                    sh 'docker push guastaferri/music-albums:1.0.1'
                }
            }
        }
    }
}

