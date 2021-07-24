package com.example.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    //most of the work
    void run(String portNumber){

        //start the server
        System.out.println("Initializing HTTP Server");

        try{

            //Create new HTTP Server object
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        //using default "executor"
        server.setExecutor(null);

        System.out.println("Creating contexts");

        // create and install the HTTP handler for the given URL path
        server.createContext("/messages", new AddMessageHandler());



        System.out.println("Starting server");
        server.start();

    }


//main method
    public static void main(String args[]){
        String portNumber = "8000";
        new Server().run(portNumber);

    }
}
