#!/usr/bin/env groovy
pipeline {
    agent none
    stages {
        stage('Publish') {
            agent { 
                label 'docker-agent' 
            }
            steps {
                 sh 'docker version'
            }
        }
    }
}

