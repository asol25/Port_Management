package Containers;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Container implements Comparable<Container> {
    //DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

    /**
     * ID of the container.
     */
    protected int ID;
    /**
     * Weight of the container.
     */
    protected int weight;

    /**
     * A constructor with two parameters, namely ID and weight.
     * @param ID ID of the container.
     * @param weight Weight of the container.
     */
    public Container(int ID, int weight) {
        this.ID = ID;
        this.weight = weight;
    }

    /**
     * An abstract method which returns fuel consumption required by the container.
     * @return Fuel consumption required by the container.
     */
    public abstract double consumption();

    /**
     * An abstract method which checks type, ID, and weight of two containers.
     * @param other Other container.
     * @return TRUE if both containers have the same type, ID, and weight, FALSE otherwise.
     */
    public abstract boolean equals(Container other);

    /**
     * Getter method for ID.
     * @return ID of the container.
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter method for weight.
     * @return Weight of the container.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Compares containers according to their ID.
     * @param other Other container.
     * @return Sign of the difference between the other container's and this container's IDs.
     */
    @Override
    public int compareTo(Container other) {
        return ID < other.ID ? -1 : (ID == other.ID ? 0 : 1);
    }

    /**
     * Sorts the containers by ID then categorizes the containers by type.
     * @param containers An ArrayList which contains containers of different types.
     * @return An ArrayList which contains ArrayLists of containers where each ArrayList corresponds to a
     * particular type of container and is sorted by ID.
     */
    static public ArrayList<ArrayList<Container>> getContainersSortedByType(ArrayList<Container> containers) {
        Collections.sort(containers);
        ArrayList<Container> basic_containers = new ArrayList<Container>();
        ArrayList<Container> heavy_containers = new ArrayList<Container>();

        for(Container container : containers)
            if(container instanceof BasicContainer)
                basic_containers.add(container);
            else
                heavy_containers.add(container);

        ArrayList<ArrayList<Container>> response = new ArrayList<ArrayList<Container>>();
        response.add(basic_containers);
        response.add(heavy_containers);

        return response;
    }
}
