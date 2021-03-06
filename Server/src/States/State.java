package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by Naknut on 11/09/14.
 */
public abstract class State {

    protected DatagramSocket socket;

    protected State(DatagramSocket socket) {
        this.socket = socket;
    }

    public abstract State nextState(DatagramPacket input) throws IOException;

    public void sendError(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "ERROR".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBusy(SocketAddress address) {
        try {
            byte[] data = "BUSY".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
