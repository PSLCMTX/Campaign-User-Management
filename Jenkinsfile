pipeline {
    agent any
    
    environment{
     PATH = "/home/ec2-user/opt/apache-maven-3.8.1/bin/:$PATH" 
    }

  stages{
     
       stage('Checkout & Build') {
           steps {
              git 'https://github.com/PSLCMTX/Campaign-User-Management'
                sh "mvn clean install package"  
           }
       }
      
  } 
}
