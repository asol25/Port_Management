package Containers;

public class BasicContainer extends Container{

    public BasicContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double consumption() {
        return weight * 1.50;
    }

    @Override
    public boolean equals(Container other) {
        return false;
    }
}
