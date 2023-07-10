module idata.visualization.visualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;


    opens idata.visualization.visualization to javafx.fxml;
    exports idata.visualization.visualization;
}