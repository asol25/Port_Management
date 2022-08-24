package Containers;

public class HeavyContainer extends Container {

    public HeavyContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double consumption() {
        return weight * 3.00;
    }

    @Override
    public boolean equals(Container other) {
        return false;
    }
}
