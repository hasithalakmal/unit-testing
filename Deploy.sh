#!/bin/sh
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>> Starting to deploy CLASS API >>>>>>>>>>>>>>>>>>>>>"
mvn clean package -DskipTests -Dmaven.test.skip=true
cd /opt/tomcat/apache-tomcat-8.5.13/
./bin/shutdown.sh
rm -rf webapps/class-api*
cd -
cp web/target/class-api.war /opt/tomcat/apache-tomcat-8.5.13/webapps/
cd /opt/tomcat/apache-tomcat-8.5.13/
./bin/startup.sh
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>> CLASS API Deployment Completed >>>>>>>>>>>>>>>>>>>>>"