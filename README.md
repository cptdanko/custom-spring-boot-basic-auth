# Basic auth in a Java Spring Boot app

This repo provides the code base for a Java Spring boot app with easy to understand code samples of how to implement basic authentication in a Java Spring boot application using Spring Security 6.

## Libraries used
1. Java 17
2. Spring Boot 3.2.1
3. Spring Security 6 and above
4. Lombok 1.18.30

### Setup and run repo

Clone the repo, go into the directory and run with M
```
git clone git@github.com:cptdanko/custom-spring-auth.git
cd custom-spring-boot-basic-auth
mvn spring-boot:run 
```

Once the spring-boot application is running, one of the ways you can test it is using Postman
1. Launch Postman
2. Import the Postman collection from the PostmanCollection folder of the repo
3. Test the 3 API endpoints, of which
  1. Say Hello and Create User are the endpoints protected with basic auth
  2. Ping user is not secure and can be called without any credentials

### How to change the default user?
To do so, change the username and password on [line 47] of the SecurityConfig class in this repo.


# Any help?
If you have difficulty understanding anything about this repo, feel free to reach out to me through this Github account or at bhuman@mydaytodo.com or bhuman.soni@gmail.com 

# More great tutorials and code samples
I will be writing a detailed tutorial on how to work with this repo on my blog. Until then refer to [my blog] for other tutorials and "how-to" articles with detailed code samples.


If you like what I am doing, you can [buy me a coffee]

[line 47]: https://github.com/cptdanko/custom-spring-boot-basic-auth/blob/main/src/main/java/com/mdt/security/demo/SecurityConfig.java#L47
[PostmanCollection]: https://github.com/cptdanko/custom-spring-boot-basic-auth/tree/main/PostmanCollection
[my blog]: https://mydaytodo.com/blog/
[blogpost]: https://mydaytodo.com/blog/
[this blog]: https://mydaytodo.com/blog/
[buy me a coffee]: https://www.buymeacoffee.com/bhumansoni
[software engineering career journey]: https://mydaytodo.com/the-3-stages-of-a-software-engineering-career/
