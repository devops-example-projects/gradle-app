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
                    gv.buildApp()
                }
            }
        }
        stage("Test Application")
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
                    gv.testApp()
                }
            }
        }
        stage("Build Docker Image")
        {
            steps
            {
                script
                {
                    gv.buildImage()
                }
            }
        }
        stage("Push Image to Docker Hub")
        {
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
            input
            {
                message "Select the environment to deploy"
                ok "Done"
                parameters
                {
                    choice(name: 'ENV', choices: ['dev','staging','prod'],description: '')
                }
            }
            steps
            {
                script
                {
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
            }
        }
    }
}