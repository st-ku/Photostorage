/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build jar and docker image") {
    container("adoptopenjdk/openjdk11:alpine") {    
        resources {
            // 500.mcpu = 0.5.cpu
            cpu = 0.4.cpu
            // 1500 MB
            memory = 1500.mb
        }
        shellScript {
            content = """
                    apk add --no-cache maven
                    mvn --version
                    mvn package
                    cp $mountDir/work/photostorage/target/photostorage-1.0-SNAPSHOT.jar $mountDir/share
                    mvn clean package
                """
        }
    }

    docker {
        beforeBuildScript {
            content = "cp $mountDir/share/photostorage-1.0-SNAPSHOT.jar docker"
        }
        build {
            context = "docker"
            file = "./docker/Dockerfile"
            labels["vendor"] = "freeitblr"
        }
        push("freeitblr.registry.jetbrains.space/p/photostorage/contreg-photostorage/photostorage") {
            // use current job run number as a tag - '0.run_number'
            tag = "0.\$JB_SPACE_EXECUTION_NUMBER"
            // use current branch name as a tag
            // tag = "\$JB_SPACE_GIT_BRANCH"
        }
    }   
    container("curlimages/curl:latest") {
        shellScript {
            //post webhook on stage server. If ip has been chenged, change the url 
          content = """
            env | grep JB_SPACE_EXECUTION_NUMBER | awk -F= '{ print "{\"tag\": \"0." $2 "\"}"}' \
 			| curl -v POST -H "Content-Type: application/json" -H "X-Hub-Signature: sha1=4542ceef4ae8a88b40e415642aab6e9be18640d5" \
			 -d @- http://3.64.126.9:9000/hooks/deploy-freeit
            """
        }
    }
}
