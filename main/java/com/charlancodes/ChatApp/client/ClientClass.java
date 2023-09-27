package com.charlancodes.ChatApp.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6690;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        ClientThread clientThread = new ClientThread(socket);
        Thread thread = new Thread(clientThread);
        thread.start();

        Scanner userInput = new Scanner(System.in);
        String message;

        while (true) {                                  //continue to take input from the user
            message = userInput.nextLine();
            clientThread.sendMessage(message);
            if (message.equalsIgnoreCase("exit"))
                break;
        }

        socket.close();
    }
}
