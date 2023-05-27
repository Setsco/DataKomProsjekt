package datakom.NodesAndSim.ActuatorNodes;

public class ThermostatActuatorNode {
    private double temperature;

    public ThermostatActuatorNode() {
        temperature = 20.0; // Default temperature
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat temperature set to: " + temperature);
    }

    
}
