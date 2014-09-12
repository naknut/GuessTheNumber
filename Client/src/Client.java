import States.InitialState;
import States.State;

import java.io.IOException;
import java.net.*;

/**
 * Created by Naknut on 11/09/14.
 */
public class Client {

    private static State state;

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetSocketAddress address = new InetSocketAddress("localhost", 1337);
            state = new InitialState(address, socket);
            while (true) {
                byte[] inBuffer = new byte[7];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                socket.receive(inPacket);
                state = state.nextState(inPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if(socket != null)
                socket.close();
        }
    }
}
