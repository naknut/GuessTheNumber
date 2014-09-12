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

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        String message = new String(data);
        System.out.println(message);
        if(message.startsWith("HELLO")) {
            sendOk(input.getSocketAddress());
            return new WatingState();
        }
        sendError(input.getSocketAddress());
        return new InitalState();
    }

    private void sendOk(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "OK".getBytes();
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
