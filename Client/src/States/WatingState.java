package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by Naknut on 12/09/14.
 */
public class WatingState extends State {

    SocketAddress address;
    DatagramSocket socket;

    public WatingState(SocketAddress address, DatagramSocket socket) {
        this.address = address;
        this.socket = socket;
        byte[] outBuffer = "START".getBytes();
        try {
            DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address);
            this.socket.send(outPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        String message = new String(data);
        System.out.println(message);
        if(message.startsWith("READY")) {
            return new GameState(address, socket);
        }
        return new InitialState(address, socket);
    }
}
