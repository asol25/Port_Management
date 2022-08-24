package Ships;

import Containers.Container;
import Ports.Port;

public interface IShip {
    public boolean sailTo(Port p);

    public void reFuel(double newFuel);

    public boolean load(Container cont);

    public boolean unLoad(Container cont);

    int compareTo(Ship other);
}
