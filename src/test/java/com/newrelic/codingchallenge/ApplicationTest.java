package com.newrelic.codingchallenge;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    private static final String LOCAL_HOST = "127.0.0.1";
    private static final int PORT = 4000;

    @Test
    public void testThroughput() {
        Client client = new Client();
        client.startConnection(LOCAL_HOST, PORT);

        Client client2 = new Client();
        client2.startConnection(LOCAL_HOST, PORT);


        for (int i = 0; i < 50000; i++) {
            int minNum = 0;
            int maxNum = 999999999;
            int random = (int) ((Math.random() * (maxNum - minNum)) + minNum);
            String randomStr = String.format("%09d", random);

            int random2 = (int) ((Math.random() * (maxNum - minNum)) + minNum);
            String randomStr2 = String.format("%09d", random2);
            client.sendMessage(randomStr2);
        }

    }

    @Test
    public void testTerminateSequence() {
        Client client = new Client();
        client.startConnection(LOCAL_HOST, 4000);
        client.sendMessage("123456789");
        client.sendMessage("terminate");
    }

    @Test
    public void testInvalidInput() {
        Client client = new Client();
        client.startConnection(LOCAL_HOST, 4000);
        client.sendMessage("123456789");
        client.sendMessage("123");
        client.sendMessage("123456789");
    }

    @Test
    public void testDuplicates() {
        Client client = new Client();
        client.startConnection(LOCAL_HOST, 4000);
        client.sendMessage("123456789");
        client.sendMessage("987654321");
        client.sendMessage("123456789");
        client.sendMessage("987654321");

    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        Client client = new Client();
        client.startConnection(LOCAL_HOST, 4000);
        String msg = client.sendMessage("hello");

        Assert.assertEquals(msg, "hello");

    }


}
