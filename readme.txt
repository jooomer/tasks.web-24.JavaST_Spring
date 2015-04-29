To run the project you have to follow next simple steps:

Check the requirements:
1. Web server: Tomcat 7
2. JVM Version: 1.8
3. Database: MySQL
4. Maven Version: 3.3

Steps to run the project:
1. Import project to your PC.

2. In pom.xml find the next properties and set the correct parameters depends of your web server and database:
<properties>
	<db.driver>com.mysql.jdbc.Driver</db.driver>
	<db.url>jdbc:mysql://localhost:3306/${db.name}</db.url>
	<db.username>root</db.username>
	<db.password>root</db.password>
	<db.dialect>org.hibernate.dialect.MySQLDialect</db.dialect>
	<tomcat.deploy.url>http://localhost:7777/manager/text</tomcat.deploy.url>
	<tomcat.deploy.server>localhost-Tomcat7</tomcat.deploy.server>
</properties>

3. In pom.xml find the tag 

1. Prepare Tomcat to deploy the project
1.1. Go to folder in witch tomcat is installed and open the file /conf/tomcat-users.xml
1.2. Inside the tag <tomcat-users> put some next tags:
			<role rolename="manager-script" />
			<role rolename="manager-gui" />
			<user username="maven" password="maven" roles="manager-gui,manager-script" />
1.3. Save this file



2. Prepare Maven 
2.1. Go to folder in witch maven is installed and open the file /conf/settings.xml
2.2. Inside the tag <servers> put some next tags:
			<server>
				<id>localhost-Tomcat7</id>
				<username>maven</username>
				<password>maven</password>
			</server>



2.2