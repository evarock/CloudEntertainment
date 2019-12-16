### Project technologies:
1) Java 8
2) Maven 3.6.2
3) MySQL 8
4) Git 2.24
5) IntelliJ IDEA Ultimate
6) Tomcat 8.5

### Projects' order:
1. Server
2. Auth-Service
3. UserService
4. (other services)
5. Zuul-Server

## Databases:
1. ent_users
2. ent_auth_users

### Steps to local launch:
1. Verify that all databases were created. First time it should be empty databases without tables.
2. Open `Server` project. All other projects should be opened too.
3. `Clean` and `install` all projects using maven.
4. Run projects using IDEA embedded Tomcat (tools in top right corner).
5. Open [http://localhost:8761] in browser. All projects except `Server` should be successfully registered.

### Steps to get User:
1. Log up via GWT Client. Do not insert user manually to database.
2. Verify that user was created to both databases.
3. Send request:
* Method: POST
* Accept: application/json
* Content-Type: application/json
* URL: http://localhost:8762/auth/
* Body:
{
    "username": username_from_s1,
    "password": password_from_s1
}
* Successful response:
{
    "username": username_from_s1,
    "email": null,
    "phone": null,
    "dob": null,
    "gender": "UNKNOWN",
    "initDate": "2019-12-16",
    "organization": false
}