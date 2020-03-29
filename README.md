# Project - News

###Description 

This project represents simple web application - news portal, where users can read and interact with 
articles (leave comments, can like or dislike articles and comments of other users).
User with role "Author" can write his own articles, and do some acts with them (delete or update).
Also he has all privileges like simple authorized user.
Admin account has all privileges like: 
   - write his own articles or comments;
   - delete or update any article;
   - delete any comment or user;
   - create account with role "Author".

During the creation of this project I learned: maven, servlet api, jsp api, MySQL database,
SQL, Hibernate, html, CSS and a little bit of base javascript.

###How to start
To start application you need complete next steps:
1. Install Java 11 or higher;

2. Install Tomcat server;

3. You should have MySQL DB and change DB name, username and password in configuration files: 
    
    - hibernate.cfg.xml in the resources of dao-hibernate-impl module if you will use dao-hibernate-impl profile;
    
    - or mysql-hikari.properties in the resources of news-web module if you will use dao-native-impl profile.

4. In the pom file of module news-service choose one of dao dependencies: native-dao or hibernate-dao;

5. In the terminal call the command: mvn clean package; You should see 'BUILD SUCCESS';

6. If fifth step completed, replace file .war from news-web module/target to the <-your tomcat package->/webapps;

7. Start your tomcat server and use this application. 