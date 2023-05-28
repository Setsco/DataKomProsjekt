package smarthouse.ActuatorNode;

public class SmartLightActuatorNode {
    private boolean isOn;
    private int brightness;

    public SmartLightActuatorNode() {
        isOn = false;
        brightness = 0;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("Smart lights turned on");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Smart lights turned off");
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println("Smart lights brightness set to: " + brightness);
    }
    
}
