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
4. Zuul-Server

## Databases:
1. ent_users
2. ent_auth_users

### Steps to local launch:
1. Verify that all databases were created. First time it should be empty databases without tables.
2. Open `Server` project. All other projects should be opened too.
3. `Clean` and `install` all projects using maven.
4. Run projects using IDEA embedded Tomcat (tools in top right corner).
5. Open [http://localhost:8761] in browser. All projects except `Server` should be successfully registered.