package Ships;

import Containers.Container;
import Containers.HeavyContainer;
import Ports.Port;

import java.util.ArrayList;
import java.util.Collections;

public class Ship implements IShip, Comparable<Ship> {

    // DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

    /**
     * ID of the Ship.
     */
    private int ID;

    /**
     * Fuel of the Ship.
     */
    private double fuel;

    /**
     * The port which the ship is currently located at.
     */
    private Port currentPort;

    /**
     * ArrayList containers which keeps track of the containers currently in the ship.
     */
    private ArrayList<Container> containers;
    /**
     * Total weight capacity of the ship.
     */
    private int totalWeightCapacity;
    /**
     * Maximum total number of containers the ship can carry at once.
     */
    private int maxNumberOfAllContainers;
    /**
     * Maximum number of heavy containers the ship can carry at once.
     */
    private int maxNumberOfHeavyContainers;

    /**
     * Fuel consumption rate per kilometer of the ship.
     */
    private double fuelConsumptionPerKM;

    /**
     * Current total weight the ship is carrying.
     */
    private int totalWeight;
    /**
     * Total number of containers the ship is carrying.
     */
    private int numberOfAllContainers;
    /**
     * Total number of heavy containers the ship is carrying.
     */
    private int numberOfHeavyContainers;


    /**
     * A constructor with eight parameters.
     *
     * @param ID                                ID of the ship.
     * @param p                                 The port where the ship is currently located at.
     * @param totalWeightCapacity               Total weight capacity of the ship.
     * @param maxNumberOfAllContainers          Maximum number of all containers the ship can carry at once.
     * @param maxNumberOfHeavyContainers        Maximum number of heavy containers the ship can carry at once.
     * @param fuelConsumptionPerKM              Fuel consumption rate per kilometer of the ship.
     */
    public Ship(int ID, Port p,
                int totalWeightCapacity,
                int maxNumberOfAllContainers,
                int maxNumberOfHeavyContainers,
                double fuelConsumptionPerKM) {
        this.ID = ID;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;

        fuel = 0.0;
        currentPort = p;
        currentPort.incomingShip(this);

        containers = new ArrayList<Container>();
        totalWeight = 0;
        numberOfAllContainers = 0;
        numberOfHeavyContainers = 0;
    }

    /**
     * Returns the list of all containers currently in the ship sorted by ID.
     * @return List of all containers currently in the ship sorted by ID.
     */
    public ArrayList<Container> getCurrentContainers() {
        Collections.sort(containers);
        return containers;
    }

    /**
     * Checks if the ship can successfully sail to the given destination port.
     * If it can be reached, sails the ship to the given destination port.
     * @param p The given destination port.
     * @return TRUE if the ship has enough fuel to sail to the given port, FALSE otherwise.
     */
    @Override
    public boolean sailTo(Port p) {
        double total_fuel_cost = fuelConsumptionPerKM;
        for (Container cont : containers)
            total_fuel_cost += cont.consumption();
        total_fuel_cost *= currentPort.getDistance(p);

        boolean can_sail = total_fuel_cost < fuel;

        if (can_sail) {
            fuel -= total_fuel_cost;
            currentPort.getCurrent().remove(this);
            currentPort.outgoingShip(this);
            p.incomingShip(this);
            currentPort = p;
        }
        return can_sail;
    }

    /**
     * Checks if the given container can be successfully loaded to the ship.
     * If it can be loaded, loads the given container to the ship.
     * @param cont The given container.
     * @return TRUE if the given container can be loaded, FALSE otherwise.
     */
    @Override
    public boolean load(Container cont) {
        boolean can_load = false;

        boolean ContainersExits = !currentPort.getContainers().contains(cont);
        boolean MaxNumbersCarry = numberOfAllContainers >= maxNumberOfAllContainers;
        boolean MaxWeightCarry = totalWeight + cont.getWeight() > totalWeightCapacity;

        if(ContainersExits || MaxNumbersCarry || MaxWeightCarry)
            can_load = false;
        else if(cont instanceof HeavyContainer) {
            if(numberOfHeavyContainers >= maxNumberOfHeavyContainers)
                can_load = false;
            else
                can_load = true;
        }
        else
            can_load = true;


        if (can_load) {
            currentPort.getContainers().remove(cont);
            containers.add(cont);
            numberOfAllContainers++;
            totalWeight += cont.getWeight();
            if (cont instanceof  HeavyContainer) {
                numberOfHeavyContainers++;
            }
        }

        return can_load;
    }

    /**
     * Method add fuel for ship.
     * @param newFuel given.
     */
    @Override
    public void reFuel(double newFuel) {
        if(newFuel < 0.0)
            throw new IllegalArgumentException();

        fuel += newFuel;
    }

    /**
     * Returns true if the given container can be successfully unloaded from a ship.
     * If it can be unloaded, unloads the given container from the ship.
     * @param cont The given container.
     * @return TRUE if the given container can be unloaded, FALSE otherwise.
     */
    @Override
    public boolean unLoad(Container cont) {
        boolean can_unload = containers.contains(cont);

        if (can_unload) {
            containers.remove(cont);
            currentPort.getContainers().add(cont);
            totalWeight -= cont.getWeight();

            if (cont instanceof HeavyContainer) {
                numberOfHeavyContainers--;
            }
        }

        return can_unload;
    }

    /**
     * Compares ships according to their ID.
     * @param other Other ship.
     * @return Sign of the difference between the other ship's and this ship's IDs.
     */
    @Override
    public int compareTo(Ship other) {
        return ID < other.ID ? -1 : (ID == other.ID ? 0 : 1);
    }

    @Override
    public String toString() {
        String str = String.format("Ship %d: %f\n", ID, fuel );
        ArrayList<ArrayList<Container>> sorted = Container.getContainersSortedByType(containers);
        for(ArrayList<Container> containers : sorted)
            if(!containers.isEmpty()) {
                str += "  " + containers.get(0).getClass().getSimpleName() + ":";
                for(Container container : containers)
                    str += " " + container.getID();
                str += "\n";
            }
        return str;
    }
}
