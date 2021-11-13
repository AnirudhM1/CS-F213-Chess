package network;

import java.io.*;
import java.net.*;

public class Server {

    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);

        // The loop runs infinitely to recieve client requests
        while (true) {
            Socket s = null;
            try {
                // Accept incoming connection request
                s = ss.accept();

                System.out.println("Client Connected: " + s);

                // Extract I/O Streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // Creating a new thread
                Thread t = new ClientHandler(dis, dos);

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
