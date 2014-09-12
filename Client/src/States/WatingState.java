package States;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Naknut on 12/09/14.
 */
public class WatingState extends State {

    DatagramSocket socket;

    public WatingState(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public State nextState(DatagramPacket input) throws IOException {
        return null;
    }
}
