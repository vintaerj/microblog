package fr.lille.univ.core;

import fr.lille.univ.core.entity.Message;
import java.util.List;

/**
 * This class exposes method to communicate with the database Microblog.
 * @author jvintaer
 * @version 1.0
 */
interface MicroBlogDao {

    /**
     * Publish a post
     * @param user the name of the user
     * @param password the password of the user
     * @param content the content
     * @return uuid the message id
     */
    String publish(String user,String password,String content);

    /**
     * Publish a post
     * @param user the name of the user
     * @param password the password of the user
     * @param content the content
     * @param messageId the reply message id
     * @return uuid the message id
     */
    String publish(String user,String password,String content,String messageId);

    /**
     * Follow a user
     * @param username the name of the user
     * @param password the password of the user
     * @param userId the uuid of the user to follow
     */
    void follow(String username,String password,String userId);

    /**
     * Get the feed of user
     * @param username the username
     * @param password the password
     * @return a list of messages.
     */
    List<Message> getFeed(String username,String password);

    /**
     * Get the feed of user
     * @param username the username
     * @param password the password
     * @param limit the limit of row
     * @return a list of messages
     */
    List<Message> getFeed(String username,String password,int limit);


    /**
     * Get view Messages
     * @return a list of last messages.
     */
    List<Message> getMessages();


}
