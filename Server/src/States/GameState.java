package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

/**
 * Created by Naknut on 12/09/14.
 */
public class GameState extends State {

    private int number;

    public GameState(int number) {
        super();
        this.number = number;
    }

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        String messageText = new String(data);
        if(messageText.startsWith("HELLO")) {
            sendBusy(input.getSocketAddress());
            return this;
        }
        ByteBuffer wrapped = ByteBuffer.wrap(data);
        int messageInt = wrapped.getInt();
        System.out.println(messageInt);
        if(messageInt < number) {
            sendLo(input.getSocketAddress());
            return this;
        } else if(messageInt > number) {
            sendHi(input.getSocketAddress());
            return this;
        } else if(messageInt == number) {
            sendCorrect(input.getSocketAddress());
            return new InitalState();
        }
        sendError(input.getSocketAddress());
        return new InitalState();
    }

    public void sendHi(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "HI".getBytes();
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

    public void sendLo(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "LO".getBytes();
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

    public void sendCorrect(SocketAddress address) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] data = "CORRECT".getBytes();
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
