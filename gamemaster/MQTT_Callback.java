package gamemaster;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTT_Callback implements MqttCallback {

    public static MQTT_Callback parseMQTT(){
        return new MQTT_Callback();
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //Handle attacks on tanks
        //handle upgrades
        /*
        switch (topic){
            case "/tanks/"
        }*/
        System.out.println(topic + " | " + mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
