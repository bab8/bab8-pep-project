package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    //Method created to verify user existence
    public Boolean verifyUser(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "SELECT * FROM account WHERE account_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set statement parameters
            ps.setInt(1, account_id);

            //execute sql
            ResultSet accountResultSet =  ps.executeQuery();
            if(accountResultSet.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Prepare sql logic
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);

            //execute sql
            ResultSet messageResultSet = ps.executeQuery();
            while(messageResultSet.next()){
                Message message = new Message(messageResultSet.getInt("message_id"), 
                                              messageResultSet.getInt("posted_by"),
                                              messageResultSet.getString("message_text"),
                                              messageResultSet.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Set statement parameters
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            //execute sql
            ps.executeUpdate();
            ResultSet messageKeyResultSet = ps.getGeneratedKeys();
            if(messageKeyResultSet.next()){
                int generatedMessageID = (int) messageKeyResultSet.getLong(1);
                return new Message(generatedMessageID, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "SELECT * FROM message WHERE message_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,message_id);

            //execute sql
            ResultSet messageResultSet = ps.executeQuery();
            while(messageResultSet.next()){
                return new Message(messageResultSet.getInt("message_id"), 
                                    messageResultSet.getInt("posted_by"),
                                    messageResultSet.getString("message_text"),
                                    messageResultSet.getLong("time_posted_epoch"));
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "DELETE * FROM message WHERE message_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,message_id);

            //execute sql
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMessageById(int message_id, Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "UPDATE message SET message_text=? WHERE message_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,message.getMessage_text());
            ps.setInt(2,message_id);

            //execute sql
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Message> getUserMessagesById(int user_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Prepare sql logic
            String sql = "SELECT * FROM message WHERE posted_by=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,user_id);

            //execute sql
            ResultSet messageResultSet = ps.executeQuery();
            while(messageResultSet.next()){
                Message message = new Message(messageResultSet.getInt("message_id"), 
                                    messageResultSet.getInt("posted_by"),
                                    messageResultSet.getString("message_text"),
                                    messageResultSet.getLong("time_posted_epoch"));
                messages.add(message);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    
}
