package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHandler extends Thread {

    private final DataInputStream dis;
    private final DataOutputStream dos;

    public ClientHandler(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String dataRecieved = dis.readUTF();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
