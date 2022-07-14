package gamemaster;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class GameMaster {
    public static final String HITBY_PATH = "tank/+/hitBy";
    public static final String POWERUP_PATH = "tank/+/powerupUsed";
    public static MqttClient client;
    static WorldState worldState = new WorldState();
    private static final GameMaster gameMasterInstance = new GameMaster();

    public static void main(String[] args) {

        try {
            client = new MqttClient(MQTT_Publish.broker, MQTT_Publish.clientID, MQTT_Publish.persistence);
            client.connect();
            client.setCallback(new MQTT_Callback());
            
            client.subscribe(HITBY_PATH, gameMasterInstance::hitByCallback);

            client.subscribe(POWERUP_PATH, gameMasterInstance::powerupCallback);
            //client.subscribe("tanks/+/hitBy");

            worldState = worldState.addPLayer("Estiaan");
            worldState = worldState.addPLayer("Tom");
            //worldState = worldState.attack(1,2);
            worldState = worldState.addPLayer("Dave");
            worldState = worldState.addPLayer("Jess");

            MQTT_Publish publish = new MQTT_Publish("","2","tank/3/hitBy",client);

        } catch (Exception me) {
            me.printStackTrace();
        }

    }

    public void hitByCallback(String topic, MqttMessage mqttMessage){
        String[] topics = topic.split("/");
        int tankNum = Integer.parseInt(topics[1]);
        //TODO: Make this work
        int numOne = Integer.parseInt(mqttMessage.toString());
        worldState = worldState.attack(numOne,tankNum);
        String st = worldState.toString();
        System.out.println(st);
    }

    public void powerupCallback(String topic, MqttMessage mqttMessage){
    }


}
