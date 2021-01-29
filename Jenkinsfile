pipeline {
agent {
        docker {
            image 'maven:3.6.3'       
            args '-v /root/.m2:/root/.m2'
        }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Make Image') {
          steps {
              echo 'Making Docker Image..'
		script {
		    dockerImage = docker.build("freeitblr.registry.jetbrains.space/p/photostorage/contreg-photostorage/photostorage:${env.BUILD_NUMBER}", "-f ./docker/Dockerfile ./target")
		 }
	    }
        }
	stage('Pushing Docker Image ') {
	    steps {
		echo 'Pushing Docker Image..'
                script {
			sh "cat ~/.ssh/.token | docker login freeitblr.registry.jetbrains.space -u viktor.n1970  --password-stdin"
			sh "docker push freeitblr.registry.jetbrains.space/p/photostorage/contreg-photostorage/photostorage:${env.BUILD_NUMBER}"
                    }
		
                }
             }
	stage('Deploy to Stage') {
            steps {
               echo 'Pushing Docker Image..'
               withCredentials([sshUserPrivateKey(credentialsId: 'staging_server', keyFileVariable: 'ssh_key', passphraseVariable: '', usernameVariable: 'user')]) {
		script {
                   sh "ssh -o StrictHostKeyChecking=no ${user}@${stage_ip} -i ~/.ssh/aws_vpn.pem  \"cat ~/.ssh/.token | docker login freeitblr.registry.jetbrains.space -u viktor.n1970  --password-stdin\""
                   sh "ssh -o StrictHostKeyChecking=no ${user}@${stage_ip} -i ~/.ssh/aws_vpn.pem \"docker pull freeitblr.registry.jetbrains.space/p/photostorage/contreg-photostorage/photostorage:${env.BUILD_NUMBER}\""
		   try {
			   sh " ssh -o StrictHostKeyChecking=no ${user}@${stage_ip} -i ~/.ssh/aws_vpn.pem \"docker stop freeit-cont\""
			   sh " ssh -o StrictHostKeyChecking=no ${user}@${stage_ip} -i ~/.ssh/aws_vpn.pem \"docker rm freeit-cont\""
                        } catch (err) {
                            echo: 'caught error: $err'
                        }
		   sh "ssh -o StrictHostKeyChecking=no ${user}@${stage_ip} -i ~/.ssh/aws_vpn.pem \"docker run --restart always --name freeit-cont -p 8080:8080 -d freeitblr.registry.jetbrains.space/p/photostorage/contreg-photostorage/photostorage:${env.BUILD_NUMBER}\""
                  }
            }
	}
        }
    }
}

