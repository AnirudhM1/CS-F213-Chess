package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
// import java.util.Scanner;

import controllers.Updater;
// import models.Move;

public class Client {

    // public static void main(String[] args) {
    // try {
    // Scanner in = new Scanner(System.in);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    private static Socket server;
    private static DataOutputStream dos;

    public static void connect(String host, int port, Updater updater) {

        try {
            server = new Socket(host, port);

            DataInputStream dis = new DataInputStream(server.getInputStream());
            dos = new DataOutputStream(server.getOutputStream());

            Thread readMessage = new Thread(() -> {
                while (true) {
                    try {
                        String msg = dis.readUTF();
                        updater.checkAndUpdate(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            readMessage.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void write(String msg) {
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
