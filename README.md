README.md

# Introduction

There will be Containers: types of containers: basic and heavy.

There are Ports and Ships. Ships are sailing betweent them. Ship are
carrying the follwing Containers.

Containers in Port can loaded containers to Ships, they can
be unloaded from a ship to port. Ships need a amount of fuel to sail from current Port to other Port.

You’re going to take the input as an input file, do the operations, and will print out the necessary information to the output file. There will be no System.in arguments, in other words, there will not be any input given with the keyboard while the program runs.

## Interface
You are already provided with fllowing interfaces: 

```
IPort.java
IShip.java
```
Do not modify the code in these files!

1. IPort.java

```
● void incomingShip(Ship s) : should add this ship to current
ArrayList.
● void outgoingShip(Ship s) : should add this ship to history
ArrayList.
```

2. IShip.java

```
● boolean sailTo(Port p) : returns true if a ship successfully
sailed to the destination port
● void reFuel(double newFuel) : adds fuel to a ship
● boolean load(Container cont) : returns true if a container was
successfully loaded to a ship
● boolean unLoad(Container cont) : returns true if a container was
successfully unloaded from a ship
```
## Class && Implemention detail

There will be 5 main classes interacting with each other in this project.

- Main
- Process
- Port
- Ship
- Container

### Main
The main method simply run application

### Process
The method simply read an input to file. You can use this class
to test your code. Scanners for input and output files and the array
lists defined below are given for you.

You are required to read from file in testcase/input and wirte to testcase/output file

### Port
Port class should have the following variables, exactly named as
below:

```
● int ID
● double X : position at location
● double Y : position at location
● ArrayList<Container> containers : contaienrs carrying at Port
● ArrayList<Ship> history : keeps track of every ship that has
visited
● ArrayList<Ship> current : keeps track of the ships currently here
```
Port must implement the IPort interface and the methods it requires.
The class should have the following methods, exactly as named below:

```
● A constructor with three parameters, ID, X, and Y.
● A method that calculates the distance between the object itself
and another Port, double getDistance(Port other)
```
### Ship

Ship class should have the following variables, exactly named as
below:

```
● int ID : ID of the Ship
● double fuel : fuel of the Ship
● Port currentPort : current Port is position Ship location at.
```

Ship must implement the IShip interface and the methods it requires.
The class should have the following methods, exactly as named below:

```
● public Ship(int ID, Port p, int totalWeightCapacity, int
maxNumberOfAllContainers, int maxNumberOfHeavyContainers, double fuelConsumptionPerKM)
● ArrayList<Container> getCurrentContainers() : should return the
list of all containers currently in the ship sorted by ID.
```

### Container
Container is an abstract class and should have the following fields:

```
● int ID : ID of the Container
● int weight : Weight of the Container
```

It should have the following methods:

```
● A constructor with parameters ID, weight
● double consumption() : should return fuel consumption required by
the container
● boolean equals(Container other) : check type, ID, and weight of a
container. If they are the same, return true; otherwise, return
false.
```
### BasicContainer and HeavyContainer
They should extend the Container class. They should have a constructor
with two inputs like a Container.

- Weight of a BasicContainer <= 3000
- Weight of a HeavyContainer : otherwise

Fuel consumption is as follows:
- BasicContainer : 1.50 per unit of weight
- HeavyContainer : 3.00 per unit of weight

## Input/Output
  
  ### Input
  
  In the first line, there is a number N, which represents the total
  number of events that occur during the simulation.
  
  This is some Action of the project:
  ```
  1. Creating a container
  2. Creating a ship
  3. Creating a port  
  4. Loading a container to a ship
  5. Unloading a container from a ship
  6. Ship sails to another port
  7. Ship is refueled
  
  ```
  
  ### Output
  
  Output to print by Port with a method toString(). A port should be
  printed as "Port ID: (x, y)", followed by IDs of containers located in
  the port: "{BasicContainer, HeavyContainer}: [IDLIST]". You should also list all ships located in
  that port as "Ship ID: FUEL_LEFT". Additionally, containers in each
  ship should be listed too.
