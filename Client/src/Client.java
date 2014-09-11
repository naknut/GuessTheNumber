import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Naknut on 11/09/14.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] outBuffer = "HELLO".getBytes();
            byte[] inBuffer = new byte["READY".getBytes().length];

            DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, InetAddress.getByName("localhost"), 1337);
            socket.send(outPacket);

            DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            socket.receive(inPacket);
            String message = new String(inPacket.getData(), "UTF-8");
            System.out.println(message);
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if(socket != null)
                socket.close();
        }
    }
}
