package datakom.Visualization;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.nio.charset.StandardCharsets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VisualizationNode{
    private MqttClient client;
    // private static final String BROKER_URL = "tcp://129.241.152.12:1883";
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String HUMIDITY_TOPIC = "humidity";
    private static final String LIGHT_TOPIC = "light";
    private static final String MOTION_TOPIC = "motion";
    private static final String TEMPERATURE_TOPIC = "temperature";

    private Label humidityLabel;
    private Label lightLabel;
    private Label motionLabel;
    private Label temperatureLabel;

    public VisualizationNode(Label humidityLabel, Label lightLabel, Label motionLabel, Label temperatureLabel) {
        this.humidityLabel = humidityLabel;
        this.lightLabel = lightLabel;
        this.motionLabel = motionLabel;
        this.temperatureLabel = temperatureLabel;

        try {
            // Create an MQTT client
            String clientId = MqttClient.generateClientId();
            client = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());

            // Connect to the MQTT broker
            client.connect();

            // Subscribe to the topics
            client.subscribe(HUMIDITY_TOPIC);
            client.subscribe(LIGHT_TOPIC);
            client.subscribe(MOTION_TOPIC);
            client.subscribe(TEMPERATURE_TOPIC);

            // Set up the message callback
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String data = new String(message.getPayload(), StandardCharsets.UTF_8);

                    // Update the UI labels on the JavaFX application thread
                    Platform.runLater(() -> {
                        if (topic.equals(HUMIDITY_TOPIC)) {
                            humidityLabel.setText("Humidity: " + data);
                        } else if (topic.equals(LIGHT_TOPIC)) {
                            lightLabel.setText("Light: " + data);
                        } else if (topic.equals(MOTION_TOPIC)) {
                            motionLabel.setText("Motion: " + data);
                        } else if (topic.equals(TEMPERATURE_TOPIC)) {
                            temperatureLabel.setText("Temperature: " + data);
                        }
                    });
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used in this example
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Other methods and functionalities of the visualization node
    // ...

    }

