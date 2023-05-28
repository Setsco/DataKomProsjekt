package smarthouse.Nodes;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TemperatureNode {
    
    private double currentTemp;
    // private static final String BROKER_URL = "tcp://129.241.152.12:1883";
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC = "temperature";
    private MqttClient client;
    private Random random = new Random();

    public double readTemperature() {
        // Simulate temperature reading
        double temperature = 20.0 + random.nextDouble() * 10.0;
        return temperature;
    }


    public TemperatureNode() {
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

    public void simulateData(){
        double minTemp = -20.0;
        double maxTemp = 50.0;
        currentTemp = minTemp + (Math.random() * (maxTemp - minTemp));

        publishTemperature();
    }

    public void publishTemperature() {
        try {
            String message = String.valueOf(currentTemp);

            // Create an MQTT message
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());

            // Publish the message to the topic
            client.publish(TOPIC, mqttMessage);
            System.out.println("Temperature data sent: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
