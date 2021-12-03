package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    public final Socket s;

    public final DataInputStream dis;
    public final DataOutputStream dos;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String dataRecieved = dis.readUTF();
                Server.transferMessage(this, dataRecieved);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
