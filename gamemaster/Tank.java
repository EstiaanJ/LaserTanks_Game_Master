package gamemaster;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Tank {
    public static final double BASE_DAMAGE = 24;

    public final double health;
    public final boolean alive;

    public final Powerup powerup;

    public final int tankNum;


    public Tank(double health, boolean alive, Powerup powerup, int tankNum) {
        this.health = health;
        this.alive = alive;
        this.powerup = powerup;
        this.tankNum = tankNum;
        //MQTT_Publish healthPublish = new MQTT_Publish("int","" + health,"tanks/tank_" + tankNum + "/health",GameMaster.client);
        //MQTT_Publish alivePublish = new MQTT_Publish("boolean","" + alive,"tanks/tank_" + tankNum + "/alive",GameMaster.client);
        //MQTT_Publish powerupPublish = new MQTT_Publish("String","" + powerup,"tanks/tank_" + tankNum + "/powerup",GameMaster.client);
    }

    private Tank byHealth(double healthIn){
        MQTT_Publish healthPublish = new MQTT_Publish("int","" + healthIn,"tank/" + tankNum + "/health",GameMaster.client);
        /*try {
            GameMaster.client.publish("tanks/tank_" + tankNum + "/health", new MqttMessage(("" + health).getBytes()));
        } catch (MqttException e) {
            throw new RuntimeException(e);
        } */
        return new Tank(healthIn, this.alive,this.powerup,this.tankNum);
    }

    private Tank byAliveStatus(boolean newStatus){
        MQTT_Publish alivePublish = new MQTT_Publish("boolean","" + alive,"tanks/tank_" + tankNum + "/alive",GameMaster.client);
        return new Tank(this.health,newStatus,this.powerup,this.tankNum);
    }

    private Tank byPowerup(Powerup inPowerup){
        MQTT_Publish powerupPublish = new MQTT_Publish("String","" + powerup,"tanks/tank_" + tankNum + "/powerup",GameMaster.client);
        return new Tank(this.health,this.alive,inPowerup,this.tankNum);
    }

    private Tank killTank(){
        MQTT_Publish alivePublish = new MQTT_Publish("boolean","" + false,"tanks/tank_" + tankNum + "/alive",GameMaster.client);
        return new Tank(-1,false,this.powerup,this.tankNum);
    }

    public Tank damageTank(double damage) {
        Tank returnTank;
        if(damage >= 0) {
            if(this.alive){
                if(this.health - damage > 0){
                    returnTank = byHealth(this.health - damage);
                } else {
                    returnTank = this.killTank();
                }
            } else {
                returnTank = this.killTank();
                System.out.println("Dead tank was damaged...");
            }
        } else {
            returnTank = this.byHealth(this.health - Math.abs(damage));
            System.err.println("Tank received negative damage! absolute value of damage was applied");
        }
        return returnTank;
    }

    public double damage(){
        return 30.0;
    }

    public String toString(){
        return "--- Tank Details ---\n" +  "\t\tHealth: " + health + "\n\t\tAlive: " + alive;
    }
}
