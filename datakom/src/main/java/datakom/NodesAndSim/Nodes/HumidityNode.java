package datakom.NodesAndSim.Nodes;

import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HumidityNode {
    private static final String QUEUE_NAME = "humidity_queue";
    private Connection connection;
    private Channel channel;
    private Random random = new Random();
    private double currentHumidity;


    public HumidityNode() {
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

    // Method to publish humidity data to RabbitMQ
    public void publishHumidity() {
        try {
            // Convert humidity data to a string message
            String message = String.valueOf(currentHumidity);

            // Publish the message to the queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Humidity data sent: " + message);
        } catch (Exception e) {
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
        // You can generate a random humidity value within a specific range or use predefined patterns
        double humidity = random.nextDouble() * 100.0; // Generate a humidity between 0.0 and 100.0
        return humidity;
    }
    
}
