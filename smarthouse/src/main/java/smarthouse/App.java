package smarthouse;

import java.util.Timer;
import java.util.TimerTask;

import smarthouse.ActuatorNode.SmartLightActuatorNode;
import smarthouse.Nodes.HumidityNode;
import smarthouse.Nodes.LightSensorNode;
import smarthouse.Nodes.MotionNode;
import smarthouse.Nodes.TemperatureNode;

/**
 * Sets up the sending of the simulation every 5 seconds
 */
public class App 
{
    public static void main( String[] args )
    {
        HumidityNode humidityNode = new HumidityNode();
        MotionNode motionNode = new MotionNode();
        LightSensorNode lightNode = new LightSensorNode(motionNode);
        TemperatureNode temperatureNode = new TemperatureNode();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                humidityNode.simulateData();
                motionNode.simulateData();
                lightNode.simulateData();
                temperatureNode.simulateData();
            }
        }, 0, 5000);// Simulate data every 5 seconds
    }
}
