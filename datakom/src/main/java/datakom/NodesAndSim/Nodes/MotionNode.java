package datakom.NodesAndSim.Nodes;

import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import datakom.NodesAndSim.ActuatorNodes.SmartLightActuatorNode;

public class MotionNode {
    private boolean motionDetected;
    private SmartLightActuatorNode lightsActuator;
    private Connection connection;
    private Channel channel;
    private static final String QUEUE_NAME = "motion_queue";
    private Random random;

    public MotionNode(SmartLightActuatorNode lightsActuator) {
        motionDetected = false;
        this.lightsActuator = lightsActuator;
        random = new Random();

        try {
            // Create a connection factory
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            // Create a connection and channel
            connection = factory.newConnection();
            channel = connection.createChannel();

            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean detectMotion() {
        motionDetected = Math.random() < 0.5; // Simulate motion randomly with a 50% chance
        return motionDetected;
    }

    public void simulateData() {
        motionDetected = random.nextBoolean();
        publishMotion();
    }

    public void publishMotion() {
        try {
            String message = String.valueOf(motionDetected);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Motion data sent: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performActionOnMotion() {
        if (motionDetected) {
            System.out.println("Motion detected! Turning on lights...");
            lightsActuator.turnOn();
        }
    }
    
}
