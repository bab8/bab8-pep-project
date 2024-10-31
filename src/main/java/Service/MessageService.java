package Service;

import java.util.List;
import DAO.MessageDAO;
import Model.Message;


public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return this.messageDAO.getAllMessages();
    }

    public Message createMessage(Message message){
        if(message.getMessage_text() != "" && message.getMessage_text().length() <= 255 && this.messageDAO.verifyUser(message.getPosted_by())){
            return this.messageDAO.createMessage(message);
        } else{
            return null;
        }
        
    }

    public Message getMessageById(int message_id){
        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id){
        Message message = getMessageById(message_id);
        if(message == null){
            this.messageDAO.deleteMessageById(message_id);
        }
        return message;
    }

    public Message updateMessageById(int message_id, Message message){
        if(getMessageById(message_id) == null && message.getMessage_text() != "" && message.getMessage_text().length() <= 255){
            this.messageDAO.updateMessageById(message_id, message);
        }
        return this.messageDAO.getMessageById(message_id);
    }

    public List<Message> getUserMessagesById(int user_id){
        return this.messageDAO.getUserMessagesById(user_id);
    }
    
}
