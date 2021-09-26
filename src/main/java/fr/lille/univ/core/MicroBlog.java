package fr.lille.univ.core;

import fr.lille.univ.core.entity.Message;
import fr.lille.univ.core.entity.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class represents Microblog database.
 * @author jvintaer
 * @version 1.0
 */
public class MicroBlog implements MicroBlogDao{

    private final String ip;
    private final int port;
    private final String role;
    private String error;

    private MicroBlogDaoImpl microBlogDao;

    public MicroBlog(String ip, int port, String role) {
        this.ip = ip;
        this.port = port;
        this.role = role;
        this.init();
    }

    private void init(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:%s/microblog", this.ip, this.port),this.role,"");
            this.microBlogDao = new MicroBlogDaoImpl(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            this.error = e.getMessage();
        }
    }

    public static List<User> load(String fileName){
        List<User> users = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> {
               users.add(new User(null,s.split("\t")[0],s.split("\t")[1].replace("\n","")));
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public String publish(String user, String password, String content) {
        return microBlogDao.publish(user, password, content);
    }

    @Override
    public String publish(String user, String password, String content, String messageId) {
        return microBlogDao.publish(user, password, content, messageId);
    }

    @Override
    public void follow(String username, String password, String userId) {
        microBlogDao.follow(username, password, userId);
    }

    @Override
    public List<Message> getFeed(String username, String password) {
        return microBlogDao.getFeed(username, password);
    }

    @Override
    public List<Message> getFeed(String username, String password, int limit) {
        return microBlogDao.getFeed(username, password, limit);
    }

    @Override
    public List<Message> getMessages() {
        return microBlogDao.getMessages();
    }

}
