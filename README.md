

#SETUP NEXUS:
docker volume create --name nexus-data
docker run -d -p 8081:8081 --name nexus -v nexus-data:/nexus-data sonatype/nexus3
password: cat nexus-data/admin.password

#SETUP SONAR:

docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest


#SETUP JENKINS:
docker run --name myjenkins -p 8080:8080 -p 50000:50000 -v /var/jenkins_home jenkins/jenkins:lts

password: cat /var/jenkins_home/secrets/initialAdminPassword


#SETUP MYSQL:

docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:latest

docker exec -it some-mysql bash

