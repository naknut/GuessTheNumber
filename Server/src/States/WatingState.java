package States;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Naknut on 11/09/14.
 */
public class WatingState extends State {
    @Override
    public State nextState(DatagramPacket input) throws IOException {
        return null;
    }
}
