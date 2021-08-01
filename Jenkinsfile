pipeline {
    agent any
    environment {
        SONARQUBE_PASSWORD = credentials('sonar-password')
        NEXUS_PASSWORD = credentials('nexus-password')
    }
    stages {
        stage('source') {
            steps {
                git branch: 'master', credentialsId: 'df481f55-c9d2-4a22-bd14-a0df3afbc99c', url: 'git@github.com:walidbenali/CICD.git'
            }
        }

        stage ('clean') {
            steps {
                echo "Maven Clean";
                sh '/opt/maven/bin/mvn clean';
            }
        }

        stage ('test') {
            steps {
                echo "Maven Test";
                sh '/opt/maven/bin/mvn test';
            }
        }
        

        stage ('install') {
            steps {
                echo "Maven install";
                sh '/opt/maven/bin/mvn -DskipTests install';
            }
        }

        stage ('qa') {
            steps {
                echo "Maven Sonar";
                sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.host.url=http://host.docker.internal:9000 -Dsonar.login=admin -Dsonar.password=$SONARQUBE_PASSWORD';
            }
        }

        stage ('Deploy') {
            steps {
                echo "Maven Deploy";
                sh '/opt/maven/bin/mvn clean package deploy:deploy-file \
                        -DgroupId=tn.esprit.spring \
                        -DartifactId=timesheet-ci \
                        -Drepo.login=admin \
                        -Drepo.pwd=newadmin \
                        -Drepo.id=deploymentRepo \
                        -Dversion=1.0 \
                        -DgeneratePom=true \
                        -Dpackaging=jar \
                        -DrepositoryId=deploymentRepo \
                        -Durl=http://host.docker.internal:8081/repository/maven-releases/ \
                        -Dfile=target/timesheet-ci-1.0.jar';
            }
        }
    }
}
