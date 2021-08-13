pipeline {
    agent any
    environment{
         NORMAL_TAG = "CIMETRICTRDDDDR"
         DOCKER_TAG = getDockerTag()
         DOCKER_PRV_TAG = getPrevDockerTag()
         IMAGE_URL_WITH_TAG = "bec0413/cmp_usr_mng:${DOCKER_TAG}"
         IMAGE_PRV_WITH_TAG = "bec0413/cmp_usr_mng:${DOCKER_PRV_TAG}"
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
               
                sshagent(['docker-machine']) {
                       
                              
                          withCredentials([string(credentialsId: 'dev-env', variable: 'devmaster')]) { 
                              
                                      sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  whoami '

                                      sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  ls -la '

                                      sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  pwd '
                                      sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  docker-compose ps -a '

                                      sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  docker-compose down '

                                      sh 'scp -o StrictHostKeyChecking=no test2.txt docker-compose.yaml ec2-user@${devmaster}:/home/ec2-user/'

                                       sh 'ssh -o StrictHostKeyChecking=no ec2-user@${devmaster}  docker-compose up --detach '

                                      sh "ssh -o StrictHostKeyChecking=no ec2-user@${devmaster} docker-compose ps -a " 
                              
}         
                            
                          }  
             
          
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
