package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by Naknut on 12/09/14.
 */
public class GameState extends State {

    SocketAddress address;
    DatagramSocket socket;

    public GameState(SocketAddress address, DatagramSocket socket) {
        this.address = address;
        this.socket = socket;
        System.out.println("Guess a number");
        Scanner guess = new Scanner(System.in);
        byte[] outBuffer = ("GUESS " + guess.next()).getBytes();
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
        if(message.startsWith("HI")) {
            System.out.println("The number is higher");
            Scanner guess = new Scanner(System.in);
            byte[] outBuffer = ("GUESS " + guess.next()).getBytes();
            try {
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address);
                this.socket.send(outPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        if(message.startsWith("LO")) {
            System.out.println("The number is lower");
            Scanner guess = new Scanner(System.in);
            byte[] outBuffer = ("GUESS " + guess.next()).getBytes();
            try {
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address);
                this.socket.send(outPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        if(message.startsWith("CORRECT")) {
            System.out.println("Your guess was correct");
        }
        return new InitialState(address, socket);
    }
}
