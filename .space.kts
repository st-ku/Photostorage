/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("build") {
    container("openjdk:11") {
        resources {
            // 500.mcpu = 0.5.cpu
            cpu = 0.4.cpu
            // 1500 MB
            memory = 1500.mb
        }
        shellScript {
            content = """
                    apt-get update
                    apt-get -y install maven
                    mvn --version
                    mvn package
                    cp target $mountDir/share
                    ls -la $mountDir/share
                    mvn clean package
                """
        }
    }

}
