package smarthouse.Nodes;


import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Represents a light sensor
 */
public class LightSensorNode {
    
    // private static final String BROKER_URL = "tcp://129.241.152.12:1883";
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC = "light";
    private MqttClient client;
    private Random random = new Random();
    private MotionNode motionNode;
    private int currentLight = 0;

    public int readLightIntensity() {
        // Simulate light intensity reading
        int lightIntensity = random.nextInt(1000); // Generate a light intensity between 0 and 1000
        return lightIntensity;
    }

    public LightSensorNode(MotionNode motionNode) {
        this.motionNode = motionNode;
        currentLight = 0;
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

    public void simulateData() {
        // Simulate data by generating random humidity values within a range
        if(motionNode.detectMotion() == true){
            int minLight = 0;
            int maxLight = 1000;
            currentLight = random.nextInt(maxLight - minLight + 1) + minLight;
        }else{
            currentLight = 0;
        }
        // Publish the simulated humidity data
        publishLight();
    }




    public void publishLight() {
        try {
            String message = String.valueOf(currentLight);

            // Create an MQTT message
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());

            // Publish the message to the topic
            client.publish(TOPIC, mqttMessage);
            System.out.println("Light data sent: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
