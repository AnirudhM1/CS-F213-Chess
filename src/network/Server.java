package network;

import java.io.*;
import java.net.*;

public class Server {

    private static final int PORT = 5000;

    private static ClientHandler handler1 = null;
    private static ClientHandler handler2 = null;

    public static void transferMessage(ClientHandler handler, String msg) {
        try {
            if (handler == handler1) {
                handler2.dos.writeUTF(msg);
            } else {
                handler1.dos.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);

        System.out.println("Server is listening on port: " + PORT);

        // The loop runs infinitely to recieve client requests
        while (true) {
            Socket s = null;
            try {

                if (handler1 != null && handler2 != null) {
                    handler1.startGame();
                    handler2.startGame();
                }

                // Accept incoming connection request
                s = ss.accept();

                if (handler1 != null && handler2 != null) {
                    System.out.println("No space available for more requests");
                    s.close();
                    s = null;
                    continue;
                }

                System.out.println("Client Connected: " + s);

                // Extract I/O Streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // Creating a new thread
                Thread t = new ClientHandler(s, dis, dos);

                // Storing the handler
                if (handler1 == null) {
                    handler1 = (ClientHandler) t;
                } else {
                    handler2 = (ClientHandler) t;
                }

                // Starting the thread
                t.start();

            } catch (Exception e) {
                s.close();
                ss.close();
                e.printStackTrace();
            }
        }
    }
}
