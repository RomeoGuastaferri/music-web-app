#!/usr/bin/env groovy
pipeline {
    agent none
    stages {
        stage('Publish') {
            agent { 
                node {label "docker-agent" }
            }
            steps {
                 sh "java -version"
            }
        }
    }
}

