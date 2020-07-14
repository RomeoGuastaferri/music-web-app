#!/usr/bin/env groovy
pipeline {
    agent { 
        node {label "docker-agent" }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
                sh 'ls -l'
            }
        }    
        stage('Test') {
            steps {
                sh 'mvn -B -P dev test'
            }
        }
        stage('Publish') {
            steps {
                sh 'docker build -f Dockerfile -t guastaferri/music-albums:1.1.0 .'
                sh 'docker images'
            }
        }
    }
}
