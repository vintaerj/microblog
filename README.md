# MicroBlog

This a small api to communicate with the microblog database.

## Interface API

You have a single class called ``MicroBlog``. You have to provide ip, port and user to create Microblog instance.
This contains methods define below :

Publish a post
```
String publish(String user,String password,String content);
String publish(String user,String password,String content,String messageId);
```

Follow user
```
void follow(String username,String password,String userId);
```

Get feed of user
```
List<Message> getFeed(String username,String password);
List<Message> getFeed(String username,String password,int limit);
```

Get last messages.

```
List<Message> getMessages();
```

## Prerequisite

- ``JDK-11``
- ``Maven``

## Compile

``
mvn clean install
``

You can use the .jar file generated in your project to communicate with the microblog database, you have just to include the microblog.jar in your classpath.


Made by **Jordan Vintaer**
 