package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageSerivce;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageSerivce = new MessageService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterUserHandeler);
        app.post("/login", this::postVerifyLoginUserHandeler);
        app.post("/messages", this::postCreateMessageHandeler);
        app.get("/messages", this::getAllMessagesHandeler);
        app.get("/messages/{message_id}", this::getMessageByIdHandeler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandeler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandeler);
        app.get("/account/{account_id}", this::getUserMessagesByIdHandeler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    private void postRegisterUserHandeler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Account account = map.readValue(context.body(), Account.class);
        Account newAccount = accountService.registerUser(account);
        if(newAccount != null){
            context.json(map.writeValueAsString(newAccount));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void postVerifyLoginUserHandeler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Account account = map.readValue(context.body(), Account.class);
        Account loginAccount = accountService.registerUser(account);
        if(accountService.verifyLoginUser(loginAccount)){
            context.json(map.writeValueAsString(loginAccount));
            context.status(200);
        } else{
            context.status(401);
        }
    }

    private void postCreateMessageHandeler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Message message = map.readValue(context.body(), Message.class);
        Message newMessage = messageSerivce.createMessage(message);
        if(newMessage != null){
            context.json(map.writeValueAsString(newMessage));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void getAllMessagesHandeler(Context context) {
        List<Message> messages = messageSerivce.getAllMessages();
        context.json(messages);
    }

    private void getMessageByIdHandeler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageSerivce.getMessageById(message_id);
        context.json(map.writeValueAsString(message));
        context.status(200);
    }

    private void deleteMessageByIdHandeler(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageSerivce.deleteMessageById(message_id);
        context.json(map.writeValueAsString(message));
        context.status(200);
    }

    private void updateMessageByIdHandeler(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = map.readValue(context.body(), Message.class);
        Message updatedMessage = messageSerivce.updateMessageById(message_id, message);
        context.json(map.writeValueAsString(updatedMessage));
        context.status(200);
    }

    private void getUserMessagesByIdHandeler(Context context) {
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messagesByAccount = messageSerivce.getUserMessagesById(account_id);
        context.json(messagesByAccount);
        context.status(200);
    }




}