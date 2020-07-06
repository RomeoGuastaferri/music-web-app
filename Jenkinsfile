#!/usr/bin/env groovy
pipeline {
    agent none
    stages {
        stage('Publish') {
            agent { 
                node {label "docker-master" }
            }
            steps {
                 sh "docker version"
            }
        }
    }
}

