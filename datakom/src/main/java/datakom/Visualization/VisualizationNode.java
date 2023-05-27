package datakom.Visualization;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VisualizationNode extends Application{
    private Connection connection;
    private Channel channel;
    private static final String HUMIDITY_QUEUE_NAME = "humidity_queue";
    private static final String LIGHT_QUEUE_NAME = "light_queue";
    private static final String MOTION_QUEUE_NAME = "motion_queue";
    private static final String TEMPERATURE_QUEUE_NAME = "temperature_queue";

    private Label humidityLabel;
    private Label lightLabel;
    private Label motionLabel;
    private Label temperatureLabel;

    public void startConsumingData() {
        try {
            // Create a connection factory
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // Set the hostname of your RabbitMQ server

            // Create a connection and channel
            connection = factory.newConnection();
            channel = connection.createChannel();

            // Declare the queues
            channel.queueDeclare(HUMIDITY_QUEUE_NAME, false, false, false, null);
            channel.queueDeclare(LIGHT_QUEUE_NAME, false, false, false, null);
            channel.queueDeclare(MOTION_QUEUE_NAME, false, false, false, null);
            channel.queueDeclare(TEMPERATURE_QUEUE_NAME, false, false, false, null);

            // Set up the consumer for each queue
            Consumer humidityConsumer = createConsumer("Humidity", HUMIDITY_QUEUE_NAME, humidityLabel);
            Consumer lightConsumer = createConsumer("Light", LIGHT_QUEUE_NAME, lightLabel);
            Consumer motionConsumer = createConsumer("Motion", MOTION_QUEUE_NAME, motionLabel);
            Consumer temperatureConsumer = createConsumer("Temperature", TEMPERATURE_QUEUE_NAME, temperatureLabel);

            // Start consuming data from the queues
            channel.basicConsume(HUMIDITY_QUEUE_NAME, true, humidityConsumer);
            channel.basicConsume(LIGHT_QUEUE_NAME, true, lightConsumer);
            channel.basicConsume(MOTION_QUEUE_NAME, true, motionConsumer);
            channel.basicConsume(TEMPERATURE_QUEUE_NAME, true, temperatureConsumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Consumer createConsumer(String sensorName, String queueName, Label labelToUpdate) {
        final Label[] label = new Label[1];
        label[0] = labelToUpdate;
    
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                Platform.runLater(() -> label[0].setText(sensorName + " data received: " + message));
            }
        };
    }
    // Other methods and functionalities of the visualization node
    // ...

    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        humidityLabel = new Label();
        lightLabel = new Label();
        motionLabel = new Label();
        temperatureLabel = new Label();

        root.getChildren().addAll(humidityLabel, lightLabel, motionLabel, temperatureLabel);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Sensor Data Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start consuming data
        startConsumingData();
    }
    public static void main(String[] args) {
        launch(args);
    }
    }

