#!/usr/bin/env groovy
pipeline {
    agent { 
        node {label "docker-agent" }
    }
    environment {
        // maven project NAME & VERSION
        pom = readMavenPom(file: 'pom.xml')
        PROJECT_NAME    = pom.getArtifactId()
        PROJECT_VERSION = pom.getVersion()

        // Docker repository, image & tag
        REPO  = "rguastaferri"
        IMAGE = "$PROJECT_NAME"
        TAG   = "$PROJECT_VERSION"
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B clean package' 
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
