package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by Naknut on 12/09/14.
 */
public class GameState extends State {

    private int number;
    private SocketAddress address;

    protected GameState(SocketAddress address, DatagramSocket socket) {
        super(socket);
        this.address = address;
        Random random = new Random();
        number = Math.abs(random.nextInt()) % 100;
        System.out.println(number);
        sendReady();
    }

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        byte[] data = input.getData();
        byte[] trimmedData = new byte[input.getLength()];
        System.out.println(input.getLength());
        System.arraycopy(data, 0, trimmedData, 0, trimmedData.length);
        String messageText = new String(trimmedData);
        if(messageText.startsWith("HELLO")) {
            sendBusy(input.getSocketAddress());
            return this;
        }

        if(!messageText.startsWith("GUESS") || messageText.split(" ").length < 2){
            sendError(input.getSocketAddress());
            return this;
        }
        int guessInt=0;
        try{
            guessInt = Integer.parseInt(messageText.split(" ")[1].toString());
        }
        catch (NumberFormatException nfe){
            sendError(input.getSocketAddress());
            return this;
        }

        System.out.println(guessInt);
        if(guessInt < number) {
            sendLo(input.getSocketAddress());
            return this;
        } else if(guessInt > number) {
            sendHi(input.getSocketAddress());
            return this;
        } else if(guessInt == number) {
            sendCorrect(input.getSocketAddress());
            return new InitalState(socket);
        }
        sendError(input.getSocketAddress());
        return new InitalState(socket);
    }

    public void sendReady() {
        try {
            socket = new DatagramSocket();
            byte[] data = "READY".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendHi(SocketAddress address) {
        try {
            byte[] data = "HI".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendLo(SocketAddress address) {
        try {
            byte[] data = "LO".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCorrect(SocketAddress address) {
        try {
            byte[] data = "CORRECT".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
