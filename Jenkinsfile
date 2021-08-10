pipeline {
    agent any
       
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3"
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
