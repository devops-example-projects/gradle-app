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
        stage("Build")
        {
            steps
            {
                script
                {
                    gv.buildApp()
                }
            }
        }
        stage("Test")
        {
            steps
            {
                script
                {
                    gv.testApp()
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