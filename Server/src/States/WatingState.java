package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Random;

/**
 * Created by Naknut on 11/09/14.
 */
public class WatingState extends State {
    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        String message = new String(data);
        System.out.println(message);
        if(message.startsWith("HELLO")) {
            sendBusy(input.getSocketAddress());
            return this;
        }
        if(message.startsWith("START")) {
            Random random = new Random();
            sendReady(input.getSocketAddress());
            return new GameState(random.nextInt() % 100);
        }
        sendError(input.getSocketAddress());
        return new InitalState();
    }

    public void sendReady(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "READY".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null)
                socket.close();
        }
    }
}
