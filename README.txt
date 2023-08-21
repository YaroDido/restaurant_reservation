1)Before running the application, you need to update the data to the database in pom.xml.

2)And the next step would be to perform the migration: mvn flyway:clean flyway:migrate

3)Start TomCat: ./bin/startup.sh in folder TomCat
4)Shotdown TomCat: ./bin/shutdown.sh
5) logs Tomcat: tail -f logs/catalina.out
6) Web-Page: http://localhost:8005/register-and-reserve/registration