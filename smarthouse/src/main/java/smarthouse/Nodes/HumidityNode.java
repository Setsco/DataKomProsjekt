package smarthouse.Nodes;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Represents a humidity sensor
 */
public class HumidityNode {
    // private static final String BROKER_URL = "tcp://129.241.152.12:1883";
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC = "humidity";
    private MqttClient client;
    private Random random = new Random();
    private double currentHumidity;


    public HumidityNode() {
        currentHumidity = 0.0;

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
    

    // Method to publish humidity data to RabbitMQ
    public void publishHumidity() {
        try {
            String message = String.valueOf(currentHumidity);

            // Create an MQTT message
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());

            // Publish the message to the topic
            client.publish(TOPIC, mqttMessage);
            System.out.println("Humidity data sent: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void simulateData(){
        double minHumidity = 30.0;
        double maxHumidity = 80.0;
        currentHumidity = minHumidity + (Math.random() * (maxHumidity - minHumidity));

        publishHumidity();
    }



    public double readHumidity() {
        // Simulate humidity reading
        double humidity = random.nextDouble() * 100.0; // Generate a humidity between 0.0 and 100.0
        return humidity;
    }
    
}

