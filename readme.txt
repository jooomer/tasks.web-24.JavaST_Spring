To run the project you have to follow next simple steps:

Check the requirements:
1. Web server: Tomcat 7
2. JVM Version: 1.8
3. Database: MySQL
4. Maven Version: 3.3

Steps to run the project:
1. Getting of the project.
1.1. Download the project and unzip it

2. Preparation of Tomcat for deploying the project
2.1. Go to the folder in which tomcat is installed and open the file /conf/tomcat-users.xml
2.2. Inside the tag <tomcat-users> put some next tags:
		<role rolename="manager-script" />
		<role rolename="manager-gui" />
		<user username="maven" password="maven" roles="manager-gui,manager-script" />
2.3. Save this file

3. Preparation of Maven 
3.1. Go to the folder in which maven is installed and open the file /conf/settings.xml
3.2. Inside the tag <servers> put some next tags:
		<server>
			<id>localhost-Tomcat7</id>
			<username>maven</username>
			<password>maven</password>
		</server>
3.3. Save this file

4. Preparation of pom.xml
4.1. In pom.xml find the next properties and set the correct parameters depends of your web server and database:
		<properties>
			<db.name>epam_store_spring</db.name>
			<db.driver>com.mysql.jdbc.Driver</db.driver>
			<db.url>jdbc:mysql://localhost:3306/</db.url>
			<db.url.name>${db.url}${db.name}</db.url.name> 
			<db.username>root</db.username>
			<db.password>root</db.password>
			<db.dialect>org.hibernate.dialect.MySQLDialect</db.dialect>
			<tomcat.url>http://localhost:7777</tomcat.url>
			<tomcat.deploy.url>${tomcat.url}/manager/text</tomcat.deploy.url>
			<tomcat.deploy.server>localhost-Tomcat7</tomcat.deploy.server>
		</properties>
4.2. Save this file

5. Run Tomcat

6. Go to the root folder of the project

7. Run maven by the command: 
mvn db:drop db:create db.schema clean tomcat7:deploy

8. If you need to redeploy the project, use next command:
mvn db:drop db:create db.schema clean tomcat7:redeploy

9. If you don't need to initialize DB, use next command:
mvn clean tomcat7:redeploy
