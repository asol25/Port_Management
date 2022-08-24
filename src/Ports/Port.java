package Ports;

import Containers.Container;
import Ships.Ship;

import java.util.ArrayList;
import java.util.Collections;

public class Port implements IPort {

    // DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

    /**
     * ID of the port.
     */
    private int ID;

    /**
     * X coordinate of the port's location.
     */
    private double X;

    /**
     * Y coordinate of the port's location.
     */
    private double Y;

    /**
     * ArrayList containers which keeps track of the containers currently in the port .
     */
    private ArrayList<Container> containers;

    /**
     * ArrayList history which keeps track of every ship has visited in the port .
     */
    private ArrayList<Ship> history;

    /**
     * ArrayList current which keeps track of the ships currently in the port .
     */
    private ArrayList<Ship> current;

    /**
     * A constructor with three parameters, namely ID, X coordinate,  Y coordinate.
     * @param ID ID of the port.
     * @param X coordinate of the port's location.
     * @param Y coordinate of the port's location.
     */
    public Port(int ID, double X, double Y) {
        this.ID = ID;
        this.X = X;
        this.Y = Y;

        containers = new ArrayList<Container>();
        history = new ArrayList<Ship>();
        current = new ArrayList<Ship>();
    }

    /**
     * GetDistance calculates distance between this port and another port .
     * @param other the other port.
     * @retun Distance between two ports.
     */
    public double getDistance(Port other) {
        double X_diff = this.X - other.X;
        double Y_diff = this.Y - other.Y;
        return Math.sqrt(X_diff * X_diff + Y_diff * Y_diff);
    }

    /**
     * Adds the given ship to current ArrayList if it is not duplicate.
     * @param ship incomingShip.
     */
    @Override
    public void incomingShip(Ship ship) {
        if (!current.contains(ship)) {
            current.add(ship);
        }
    }

    /**
     * Adds the given ship to history ArrayList if it is not a duplicate.
     * @param ship outgoingShip.
     */
    @Override
    public void outgoingShip(Ship ship) {
        if (!history.contains(ship)) {
            history.add(ship);
        }
    }

    /**
     * Getter method for containers.
     * @return An ArrayList which keeps track of the containers currently in the port.
     */
    public ArrayList<Container> getContainers() {
        return containers;
    }

    /**
     * Getter method for current.
     * @return An ArrayList which keeps track of the ships currently in the port.
     */
    public ArrayList<Ship> getCurrent() {
        return current;
    }


    @Override
    public String toString() {
        String str = String.format("Port %d: (%.2f, %.2f)\n", ID, X, Y);
        ArrayList<ArrayList<Container>> sorted = Container.getContainersSortedByType(containers);
        for(ArrayList<Container> containers : sorted)
            if(!containers.isEmpty()) {
                str += "  " + containers.get(0).getClass().getSimpleName() + ":";
                for(Container container : containers)
                    str += " " + container.getID();
                System.out.println(str);
                str += "\n";
            }

        Collections.sort(current);
        for(Ship ship : current)
            str += ship.toString();

        return str;
    }
}
