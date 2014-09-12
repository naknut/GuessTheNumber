package States;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Naknut on 12/09/14.
 */
public abstract class State {
    public abstract State nextState(DatagramPacket input) throws IOException;
}
