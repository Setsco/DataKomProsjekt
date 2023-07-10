package idata.visualization.visualization;

import idata.visualization.visualization.VisualizationNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Shows the data received in a javaFX application
 */
public class App extends Application
{
    VisualizationNode visualizationNode;
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        Label humidityLabel = new Label();
        Label lightLabel = new Label();
        Label motionLabel = new Label();
        Label temperatureLabel = new Label();

        root.getChildren().addAll(humidityLabel, lightLabel, motionLabel, temperatureLabel);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Sensor Data Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start consuming data
        visualizationNode = new VisualizationNode(humidityLabel, lightLabel, motionLabel, temperatureLabel);
    }
}

