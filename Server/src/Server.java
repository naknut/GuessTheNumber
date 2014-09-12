import States.InitalState;
import States.State;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Naknut on 11/09/14.
 */
public class Server {

    static State currentState = new InitalState();

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1337);
        byte[] buffer = new byte["CORRECT".getBytes().length];
        while(true){
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            currentState = currentState.nextState(packet);
        }
    }
}
