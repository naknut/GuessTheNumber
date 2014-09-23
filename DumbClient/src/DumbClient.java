import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by Naknut on 23/09/14.
 */
public class DumbClient {
    public static void main(String[] args) throws SocketException {
        InetSocketAddress address = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
        byte[] inBuffer = new byte[7];
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                byte[] outBuffer = scanner.nextLine().getBytes();
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address);
                socket.send(outPacket);
                socket.receive(inPacket);
                System.out.println(new String(inBuffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
