package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerUserHandeler);
        app.post("/login", this::VerifyLoginUserHandeler);
        app.post("/login", this::createMessageHandeler);
        app.get("/login", this::getAllMessagesHandeler);
        app.get("/login", this::getMessageByIdHandeler);
        app.delete("/login", this::deleteMessageByIdHandeler);
        app.patch("/login", this::updateMessageByIdHandeler);
        app.get("/login", this::getUserMessagesByIdHandeler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerUserHandeler(Context context) {
        context.json("sample text");
    }

    private void VerifyLoginUserHandeler(Context context) {
        context.json("sample text");
    }

    private void createMessageHandeler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandeler(Context context) {
        context.json("sample text");
    }

    private void getMessageByIdHandeler(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByIdHandeler(Context context) {
        context.json("sample text");
    }

    private void updateMessageByIdHandeler(Context context) {
        context.json("sample text");
    }

    private void getUserMessagesByIdHandeler(Context context) {
        context.json("sample text");
    }




}