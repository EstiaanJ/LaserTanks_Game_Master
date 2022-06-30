package gamemaster;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class GameMaster {
    public static MqttClient client;
    static WorldState worldState = new WorldState();
    public static void main(String[] args) {


        try {
            client = new MqttClient(MQTT_Publish.broker, MQTT_Publish.clientID, MQTT_Publish.persistence);
            client.connect();


            worldState.addPLayer("Estiaan");
            worldState.addPLayer("Tom");
            worldState.attack(1,2);

            worldState.addPLayer("Dave");
            worldState.addPLayer("Jess");

            MQTT_Publish publish = new MQTT_Publish("","3,4","tanks/attacks",client);
            client.disconnect();
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }
}
