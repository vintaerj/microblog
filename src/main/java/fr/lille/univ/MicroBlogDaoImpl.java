package fr.lille.univ;

import fr.lille.univ.entity.Message;

import java.sql.*;
import java.util.List;

/**
 *
 */
class MicroBlogDaoImpl implements MicroBlogDao
{

    private Connection connection;

    @Override
    public String publish(String user, String password, String content) {
        return null;
    }

    @Override
    public String publish(String user, String password, String content, String messageId) {
        try (CallableStatement cst = this.connection.prepareCall("{call insert_message(?,?,?,?)}")) {
            cst.setString(1, user);
            cst.setString(2, password);
            cst.setString(3, content);
            cst.setObject(4, messageId);

            final ResultSet rs = cst.executeQuery();
            return rs.next() ? rs.getString("uuid") : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void follow(String username, String password, String userId) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT follow(?,?,?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setObject(3, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFeed(String username, String password) {

    }

    @Override
    public void getFeed(String username, String password, int limit) {

    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}
