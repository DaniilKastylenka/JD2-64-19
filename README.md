# Project - News

To start application you need complete next steps:
1. Install Java 11 or higher;

2. Install Tomcat server;

3. You should have MySQL DB and change DB name, username and password in configuration files: 
hibernate.cfg.xml in the resources of dao-hibernate-impl module if you will use dao-hibernate-impl profile or
mysql-hikari.properties in the resources of news-web module if you will use dao-native-impl profile;

4. In the pom file of module news-service choose property 'true' on activationByDefault tag of the profile, that you will use: dao-native-impl or dao-hibernate-impl;

5. In the terminal call the command: mvn clean package; You should see 'BUILD SUCCESS';

6. If fifth step completed, replace file .war from news-web module/target to the <your tomcat package>/webapps;

7. Start your tomcat server and use this application. 