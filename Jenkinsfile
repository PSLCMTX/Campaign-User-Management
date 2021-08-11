pipeline {
    agent any
    environment{
     
         DOCKER_TAG = getDockerTag()
         DOCKER_PRV_TAG = getPrevDockerTag()
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
      
      stage('Build & Push Docker Image'){
            steps{
                sh "docker build . -t ${IMAGE_URL_WITH_TAG}"
                    withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhubpass')]) {
                                 sh "docker login -u bec0413 -p  ${dockerhubpass}"
                                 sh "docker push ${IMAGE_URL_WITH_TAG}"
                        }
                
               
            }
        }
      stage('Deployment on Dockerhost'){
      steps{
                sh "chmod +x versionChange.sh"
                sh "./versionChange.sh ${DOCKER_TAG}"
                sh "cat docker-compose.yaml"
       
                 
            ansiblePlaybook becomeUser: 'ec2-user', credentialsId: 'docker-machine', installation: 'ansible', inventory: 'hosts', playbook: 'ansible.yaml', sudoUser: 'ec2-user'
          
           }
      }
  } 
}
def getDockerTag(){
    def tag  = sh script: 'git rev-parse --short HEAD', returnStdout: true
    return tag
}

def getPrevDockerTag(){
    def tag  = sh script: 'git rev-parse --short @~', returnStdout: true
    return tag
}
