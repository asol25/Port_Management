package Ports;

import Ships.Ship;

public interface IPort {

    public void outgoingShip(Ship s);

    public void incomingShip(Ship s);
}
