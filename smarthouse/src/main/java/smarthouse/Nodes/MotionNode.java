package smarthouse.Nodes;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import smarthouse.ActuatorNode.SmartLightActuatorNode;

public class MotionNode {
    private boolean motionDetected;
    private SmartLightActuatorNode lightsActuator;
    // private static final String BROKER_URL = "tcp://129.241.152.12:1883";
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC = "motion";
    private MqttClient client;
    private Random random;

    public MotionNode() {
        motionDetected = false;
        random = new Random();

        try {
            // Create an MQTT client
            String clientId = MqttClient.generateClientId();
            client = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());

            // Connect to the MQTT broker
            client.connect();

            // Subscribe to the topic
            client.subscribe(TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean detectMotion() {
        return motionDetected;
    }

    public void simulateData() {
        motionDetected = random.nextBoolean();
        publishMotion();
    }

    public void publishMotion() {
        try {
            String message = String.valueOf(motionDetected);

            // Create an MQTT message
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());

            // Publish the message to the topic
            client.publish(TOPIC, mqttMessage);
            System.out.println("Motion data sent: " + message);
        } catch (MqttException e) {
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
