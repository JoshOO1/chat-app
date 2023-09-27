package com.charlancodes.ChatApp.client;

import com.charlancodes.ChatApp.ThreadColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;
    private PrintWriter printToTheServer;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(ThreadColor.ANSI_YELLOW + "Client Started");
            System.out.println(ThreadColor.ANSI_CYAN + "Write Message to be Sent: ");
            BufferedReader readToTheClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printToTheServer = new PrintWriter(socket.getOutputStream(), true);

            String messageFromServer;
            while ((messageFromServer = readToTheClient.readLine()) != null) {
                System.out.println(ThreadColor.ANSI_RED + "Client says: " + messageFromServer);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        printToTheServer.println(message);
    }
}
