pipeline {
    agent { label "jenkins-slave" }
    stages {
        stage('cleanup') {
            steps {
                sh 'cd $WORKSPACE'
                sh 'docker stop eager_mahavira || true'
                sh 'docker rm -f eager_mahavira || true'
                // sh 'docker-compose down || true'
            }
        }
        stage('build') {
            steps {
                  sh 'cd $WORKSPACE'
                  sh 'docker pull pengbai/docker-supermario:latest'
                  sleep(time: 3, unit: "SECONDS")
                }
        }
        stage('deploy') {
            steps {
                sh 'cd $WORKSPACE && docker run -d -p 8600:8080 pengbai/docker-supermario'
                sleep(time: 3, unit: "SECONDS")
            }
        }
    }
}