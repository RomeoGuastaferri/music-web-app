#!/usr/bin/env groovy
pipeline {
    agent { 
        node {label "docker-agent" }
    }
    options {
        // abort build on test & quality gate failures
        skipStagesAfterUnstable()
    }
    environment {
        // maven project NAME & VERSION
        pom = readMavenPom(file: 'pom.xml')
        PROJECT_GROUP   = pom.getGroupId()
        PROJECT_VERSION = pom.getVersion()

        // Docker repository, image & tag
        REPO  = "rguastaferri"
        IMAGE = "$PROJECT_NAME"
        TAG   = "$PROJECT_VERSION"

        // Sonarqube parameters
        SONAR_DIR = tool 'sonar-scanner';
    }
    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn -B clean package' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Sonar Scan') {
            steps {
                withSonarQubeEnv('sonarqube-in-azure') {
                    sh 'mvn -B sonar:sonar'
                    sh "${SONAR_DIR}/bin/sonar-scanner"
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Publish') {
            steps {
                // build docker image & push to dockerhub
                script{
                    image = docker.build("$REPO/$IMAGE:$TAG")
                    docker.withRegistry('', 'DockerHubCredentials') {
                        image.push()
                    }
                }

                // deploy docker image to azure app service
                azureWebAppPublish ([
                    azureCredentialsId: "AzureAppServiceCredentials",
                    resourceGroup: "music-albums-rg",
                    appName: "music-albums-app",
                    slotName: "dev",
                    publishType: "docker",
                    dockerImageName: "$REPO/$IMAGE",
                    dockerImageTag: "$TAG",
                    dockerRegistryEndpoint: [credentialsId: "DockerHubCredentials", url: ""],
                    skipDockerBuild: "true"
                ])
            }
        }
    }
}
