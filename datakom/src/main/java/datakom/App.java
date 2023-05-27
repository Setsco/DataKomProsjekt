package datakom;

import java.util.Timer;
import java.util.TimerTask;

import datakom.NodesAndSim.ActuatorNodes.SmartLightActuatorNode;
import datakom.NodesAndSim.Nodes.HumidityNode;
import datakom.NodesAndSim.Nodes.LightSensorNode;
import datakom.NodesAndSim.Nodes.MotionNode;
import datakom.NodesAndSim.Nodes.TemperatureNode;
import datakom.Visualization.VisualizationNode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HumidityNode humidityNode = new HumidityNode();
        LightSensorNode lightNode = new LightSensorNode();
        SmartLightActuatorNode lightsActuator = new SmartLightActuatorNode();
        MotionNode motionNode = new MotionNode(lightsActuator);
        TemperatureNode temperatureNode = new TemperatureNode();

        // Create an instance of the visualization node
        VisualizationNode visualizationNode = new VisualizationNode();

        // Start consuming data in the visualization node
        visualizationNode.startConsumingData();

        // Simulate and publish data from the sensor nodes
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                humidityNode.simulateData();
                lightNode.simulateData();
                motionNode.simulateData();
                temperatureNode.simulateData();
            }
        }, 0, 5000);// Simulate data every 5 seconds
    }
}

