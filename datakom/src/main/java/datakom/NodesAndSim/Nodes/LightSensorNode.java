package datakom.NodesAndSim.Nodes;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Random;

public class LightSensorNode {
    
    private int currentLight;
    private static final String QUEUE_NAME = "light_queue";
    private Connection connection;
    private Channel channel;
    private Random random = new Random();

    public int readLightIntensity() {
        // Simulate light intensity reading
        // You can generate a random light intensity value within a specific range or use predefined patterns
        int lightIntensity = random.nextInt(1000); // Generate a light intensity between 0 and 1000
        return lightIntensity;
    }

    public LightSensorNode() {
        try {
            // Create a connection factory
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // Set the hostname of your RabbitMQ server

            // Create a connection and channel
            connection = factory.newConnection();
            channel = connection.createChannel();

            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void simulateData() {
        // Simulate data by generating random humidity values within a range
        int minLight = 0;
        int maxLight = 1000;
        currentLight = random.nextInt(maxLight - minLight + 1) + minLight;

        // Publish the simulated humidity data
        publishLight();
    }




    public void publishLight() {
        try {
            String message = String.valueOf(currentLight);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Light density data sent: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
