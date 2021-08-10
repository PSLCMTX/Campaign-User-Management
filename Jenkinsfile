pipeline {
    agent any
        
  stages{
     
       stage('Checkout & Build') {
              git 'https://github.com/PSLCMTX/Campaign-User-Management'
                sh "mvn clean install package"  
       }
      
  } 
}
