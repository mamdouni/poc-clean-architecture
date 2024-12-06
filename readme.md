# POC Clean Architecture

After starting the server, you can check if the api is running by accessing the following url :
- http://localhost:8080/actuator/health

You can access the database via :
- http://localhost:8080/h2-console
using the following credentials:
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: password

![h2-connect.png](images/h2-connect.png)

You can also test the api using these examples :
- http://localhost:8080/users
- http://localhost:8080/users/1
- http://localhost:8080/users/99
- http://localhost:8080/users/1/tasks/2