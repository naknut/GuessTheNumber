package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by Naknut on 12/09/14.
 */
public class InitialState extends State {

    private DatagramSocket socket;
    private SocketAddress address;

    public InitialState(SocketAddress address, DatagramSocket socket) {
        this.socket = socket;
        this.address = address;
        byte[] outBuffer = "HELLO".getBytes();
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
        if(message.startsWith("HELLO")) {
            return new WatingState(address, socket);
        }
        return new InitialState(address, socket);
    }
}
