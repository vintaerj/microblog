package fr.lille.univ.core;

import fr.lille.univ.core.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the methods to communicate with the MicroBlog database.
 * @author jvintaer
 * @version 1.0
 */
class MicroBlogDaoImpl implements MicroBlogDao
{

    private static final int DEFAULT_LIMIT = 50;

    private final Connection connection;

    public MicroBlogDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public synchronized String publish(String user, String password, String content) {
        return this.publish(user, password, content, null);
    }

    @Override
    public synchronized String publish(String user, String password, String content, String messageId) {
        try (CallableStatement cst = this.connection.prepareCall("{call insert_message(?,?,?,cast(? as uuid))}")) {
            cst.setString(1, user);
            cst.setString(2, password);
            cst.setString(3, content);
            cst.setString(4, messageId);

            final ResultSet rs = cst.executeQuery();
            return rs.next() ? rs.getString("result") : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized void follow(String username, String password, String userId) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("select * from follow(?,?,cast(? as uuid))")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Message> getFeed(String username, String password) {
        return this.getFeed(username,password,DEFAULT_LIMIT);
    }

    @Override
    public synchronized List<Message> getFeed(String username, String password, int limit) {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM feed(?,?) LIMIT ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, limit);

            final ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                messages.add(new Message(rs.getString("message_id"),rs.getString("content"),rs.getDate("published_date"), rs.getString("replies_to"),null,rs.getString("user_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public synchronized List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * from messages")) {
            final ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                messages.add(new Message(rs.getString("message_id"),rs.getString("content"),rs.getDate("published_date"), rs.getString("replies_to"),rs.getString("user_id"), null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
