import Containers.BasicContainer;
import Containers.Container;
import Containers.HeavyContainer;
import Ports.Port;
import Ships.Ship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Process {
    static File initialFile = new File("src/text.txt");
    static File result = new File("src/out.txt");

   static Scanner scanner;

    static {
        try {
            scanner = new Scanner(initialFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void displayMenu() throws FileNotFoundException {
        ArrayList<Container> containers = new ArrayList<>();
        ArrayList<Port> ports = new ArrayList<>();
        ArrayList<Ship> ships = new ArrayList<>();

        int N = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < N; i++) {	// Iterate over N lines.
            getMenu(containers, ships, ports);
        }
        result(ports);
    }

    private void  getMenu(ArrayList<Container> containers, ArrayList<Ship> ships, ArrayList<Port> ports) {
        final int operation_type = scanner.nextInt();
        switch(operation_type) {
            case 1: {		// Create a container.
                final int cont_ID = containers.size();
                final int port_ID = scanner.nextInt();
                final int weight = scanner.nextInt();
                Container container = null;

                    int HeavyWeight = 3000;
                    if(weight > HeavyWeight)
                        container = new HeavyContainer(cont_ID, weight);
                    else
                        container = new BasicContainer(cont_ID, weight);

                ports.get(port_ID).getContainers().add(container);
                containers.add(container);
                break; }

            case 2: {		// Create a ship.
                final int ship_ID = ships.size();
                final int port_ID = scanner.nextInt();
                final int totalWeightCapacity = scanner.nextInt();
                final int maxNumberOfAllContainers = scanner.nextInt();
                final int maxNumberOfHeavyContainers = scanner.nextInt();
                final double fuelConsumptionPerKM = scanner.nextDouble();

                // Add the generated ship to the port's "current" ArrayList and the general "ships" ArrayList.
                ships.add(new Ship(ship_ID, ports.get(port_ID),
                        totalWeightCapacity,
                        maxNumberOfAllContainers,
                        maxNumberOfHeavyContainers,
                        fuelConsumptionPerKM));
                break; }

            case 3: {		// Create a port.
                final int port_ID = ports.size();
                final double X = scanner.nextDouble();
                final double Y = scanner.nextDouble();

                // Add the generated port to the "ports" ArrayList.
                ports.add(new Port(port_ID, X, Y));
                break; }

            case 4: {		// Load a container to a ship.
                final int ship_ID = scanner.nextInt();
                final int cont_ID = scanner.nextInt();
                ships.get(ship_ID).load(containers.get(cont_ID));
                break; }

            case 5: {		// Unload a container from a ship.
                final int ship_ID = scanner.nextInt();
                final int cont_ID = scanner.nextInt();
                ships.get(ship_ID).unLoad(containers.get(cont_ID));
                break; }

            case 6: {		// Sail ship to another port.
                final int ship_ID = scanner.nextInt();
                final int port_ID = scanner.nextInt();
                ships.get(ship_ID).sailTo(ports.get(port_ID));
                break; }

            case 7: {		// Refuel ship.
                final int ship_ID = scanner.nextInt();
                final double fuel = scanner.nextDouble();
                ships.get(ship_ID).reFuel(fuel);
                break; }

            default:		// Invalid operation.
                System.out.println("Invalid operation.");
        }
    }

    private void result(ArrayList<Port> ports) throws FileNotFoundException {
        // Create a new PrintStream object to read data from the input file with directory "args[1]".
        PrintStream out = new PrintStream(result);

        for(Port port : ports)
            out.print(port.toString());		// Prints the ports' content to the output file.

        out.close();
    }
}
