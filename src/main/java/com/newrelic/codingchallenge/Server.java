package com.newrelic.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public final class Server {

    private static final int MAX_CLIENTS = 5;
    //Using BlockingQueues here to improve speed and to be thread safe.
    private final BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
    //Using Executor Utility class which provides factory methods
    //Internally manages thread pool of MAX_CLIENTS
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LogWriterTask logWriterTask = new LogWriterTask(blockingQueue);
        //Submits a runnable task for execution and returns a Future representing that task.
        executorService.submit(logWriterTask);

        while (true) {
            try {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept(), blockingQueue, executorService);
                executorService.submit(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler implements Runnable {

        private final BlockingQueue<Integer> blockingQueue;
        private final Socket clientSocket;
        private final ExecutorService executorService;
        // Constructs a handler thread; stores away a ClientSocket and sets up the BlockingQueue
        public ClientHandler(Socket socket, BlockingQueue<Integer> blockingQueue, ExecutorService executorService) {
            this.clientSocket = socket;
            this.blockingQueue = blockingQueue;
            this.executorService = executorService;
        }

        @Override
        public void run() {

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Client connection started");
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }

                    String inputLine = "";
                    try {
                        inputLine = in.readLine();
                    } catch (SocketException e) {
                        e.printStackTrace();
                        break;
                    }

                    if (inputLine == null) {
                        break;
                    }

                    if (inputLine.equals("terminate")) {
                        executorService.shutdownNow();
                        break;
                    }

                    blockingQueue.add(parseInt(inputLine));
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            } finally {
                closeSocket();
            }
        }

        private int parseInt(String inputLine)
                throws NumberFormatException {
            if (inputLine.length() != 9) {
                throw new NumberFormatException("Input has invalid length: '" + inputLine + "'");
            }

            int number = Integer.parseInt(inputLine);

            if (number < 0) {
                throw new NumberFormatException("Input contained a minus sign: '" + inputLine + "'");
            }

            return number;
        }

        private void closeSocket() {
            try {
                clientSocket.close();
                System.out.println("Client connection closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}