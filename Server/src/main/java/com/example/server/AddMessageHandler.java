package com.example.server;

import com.example.model.Message;
import com.example.model.MessagesResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;


public class AddMessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;
        MessagesResult mesagesResult = null;
        Message addMessage;
        String username;
        ServerModel serverModel = ServerModel.getInstance();
        Gson gson = new Gson();

        try{
            //check request method
            if (exchange.getRequestMethod().toLowerCase().equals("post")){
                InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
                gson = new Gson();
                addMessage = gson.fromJson(reqBody, Message.class);
                //check response for error
                serverModel.addMessage(addMessage.getUsername(), addMessage);
                //return OK
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                respBody.close();

            }
            else if(exchange.getRequestMethod().toLowerCase().equals("get")){
                InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
                gson = new Gson();
                username = gson.fromJson(reqBody, String.class);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonStr = gson.toJson(ServerModel.getInstance().getMessages(username));
                respBody.write(jsonStr);
                respBody.flush();
                respBody.close();
            }
            if(!success){
                //return for bad input
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonStr = gson.toJson("Bad Input");
                respBody.write(jsonStr);
                respBody.flush();
                respBody.close();
            }

        }catch(IOException e){
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

}