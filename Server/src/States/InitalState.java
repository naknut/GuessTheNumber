package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by Naknut on 11/09/14.
 */
public class InitalState extends State {

    public InitalState(DatagramSocket socket) {
        super(socket);
    }

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        String message = new String(data);
        System.out.println(message);
        if(message.startsWith("HELLO")) {
            return new WatingState(input.getSocketAddress(), socket);
        }
        sendError(input.getSocketAddress());
        return new InitalState(socket);
    }
}
