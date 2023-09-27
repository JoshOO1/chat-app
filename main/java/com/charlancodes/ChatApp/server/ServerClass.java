package com.charlancodes.ChatApp.server;

import com.charlancodes.ChatApp.ThreadColor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerClass {
    private static final int PORT = 6690;
    private static List<ClientHandler> clients = new ArrayList<>();
    static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        System.out.println(ThreadColor.ANSI_YELLOW + "Server Started: Waiting for Client Requests");
        try{serverSocket = new ServerSocket(PORT);}
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println(ThreadColor.ANSI_PURPLE + "Connection Established on Port " + PORT);

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            Thread clientThread = new Thread(clientHandler);
            clientThread.start();
        }
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
