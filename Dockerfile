FROM tomcat:10.1-jre17
COPY target/java-web-app-1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080

