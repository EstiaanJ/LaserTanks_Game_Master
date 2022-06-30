package gamemaster;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTT_Publish {
    public static final String broker = "tcp://192.168.8.2:1883";
    public static final String clientID = "LTGM";

    public static MemoryPersistence persistence = new MemoryPersistence();

    public final String messageType;
    public final String message;
    public final String topic;

    public final MqttClient mqttClient;

    public MQTT_Publish(String messageType, String message, String topic, MqttClient mqttClient) {
        this.messageType = messageType;
        this.message = message;
        this.topic = topic;
        this.mqttClient = mqttClient;
        int qos = 0;

        try {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            //this.mqttClient.connect(connOpts);
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(qos);
            this.mqttClient.publish(topic, mqttMessage);
            //this.mqttClient.disconnect();

        } catch (MqttException me){
            me.printStackTrace();
        }
    }
}
