package datakom.NodesAndSim.Nodes;

import java.util.Random;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class TemperatureNode {
    
    private double currentTemp;
    private static final String QUEUE_NAME = "temperature_queue";
    private Connection connection;
    private Channel channel;
    private Random random = new Random();

    public double readTemperature() {
        // Simulate temperature reading
        double temperature = 20.0 + random.nextDouble() * 10.0;
        return temperature;
    }


    public TemperatureNode() {
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

    public void simulateData(){
        double minTemp = -20.0;
        double maxTemp = 50.0;
        currentTemp = minTemp + (Math.random() * (maxTemp - minTemp));

        publishTemperature();
    }

    public void publishTemperature() {
        try {
            String message = String.valueOf(currentTemp);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Temperature data sent: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
