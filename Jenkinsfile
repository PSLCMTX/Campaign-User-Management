pipeline {
    agent any
    environment{
     
         DOCKER_TAG = getDockerTag()
        IMAGE_URL_WITH_TAG = "bec0413/cmp_usr_mng:${DOCKER_TAG}"
    }
   tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3"
    } 
  stages{
     
       stage('Checkout & Build') {
           steps {
              git 'https://github.com/PSLCMTX/Campaign-User-Management'
                sh "mvn clean install package"  
                sh "echo $IMAGE_URL_WITH_TAG"
           }
       }
      
  } 
}
def getDockerTag(){
    def tag  = sh script: 'git rev-parse HEAD', returnStdout: true
    return tag
}
