def buildApp() 
{
    echo "Building the Application"
    sh "chmod +x gradlew"
    sh "./gradlew build"
}

def testApp()
{
    echo "Testing the Application"
    sh "chmod +x gradlew"
    sh "./gradlew test"
}

def buildImage()
{
    echo "Building the Docker Image"
    sh "docker build -t gradle-app ."
    sh "docker tag gradle-app adityadevops/gradle-app:${env.BUILD_ID}"
}

def pushImage()
{
    echo "Pushing the Image to Docker Hub"
    withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDENTIALS', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) 
    {
        sh 'echo ${DOCKERHUB_PASSWORD} | docker login -u ${DOCKERHUB_USERNAME} --password-stdin'
    }
    sh "docker push adityadevops/gradle-app:${env.BUILD_ID}"
}

def removeImage()
{
    sh "docker rmi gradle-app"
    sh "docker rmi adityadevops/gradle-app:${env.BUILD_ID}"
}

def deployApp()
{
    echo "Deploying App"
}

return this