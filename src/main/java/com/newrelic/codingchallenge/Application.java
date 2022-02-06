package com.newrelic.codingchallenge;


public class Application {
    static final int PORT = 4000;

    public static void main(String[] args) {
        System.out.println("Starting up server ....");
        Server server = new Server();
        //Starting server
        server.start(PORT);
    }
}