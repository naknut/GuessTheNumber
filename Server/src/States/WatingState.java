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

    SocketAddress address;

    protected WatingState(SocketAddress address, DatagramSocket socket) {
        super(socket);
        try {
            socket.setSoTimeout(10000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.address = address;
        sendOk();
    }

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
            return new GameState(address, socket);
        }
        sendError(input.getSocketAddress());
        return new InitalState(socket);
    }

    private void sendOk() {
        try {
            byte[] data = "OK".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
