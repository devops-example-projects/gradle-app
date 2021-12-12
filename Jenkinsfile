#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv

pipeline 
{
    agent any
    stages 
    {
        stage("Init")
        {
            steps
            {
                script
                {
                    gv = load "script.groovy"
                }
            }
        }
        stage("Build Application")
        {
            steps
            {
                script
                {
                    buildApp()
                }
            }
        }
        stage("Test Application")
        {
            steps
            {
                script
                {
                    testApp()
                }
            }
        }
        stage("Build Docker Image")
        {
            steps
            {
                script
                {
                    buildImage 'adityadevops/gradle-app'
                }
            }
        }
        stage("Push Image to Docker Hub")
        {
            when
            {
                expression
                {
                    BRANCH_NAME == "main"
                }
            }
            steps
            {
                script
                {
                    gv.pushImage()
                }
            }
        }
        stage("Remove Image")
        {
            when
            {
                expression
                {
                    BRANCH_NAME == "main"
                }
            }
            steps
            {
                script
                {
                    gv.removeImage()
                }
            }
        }
        stage("Deploy")
        {
            steps
            {
                script
                {
                    gv.deployApp()
                }
            }
        }
    }
}