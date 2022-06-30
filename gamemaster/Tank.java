package gamemaster;

public class Tank {
    public static final double BASE_DAMAGE = 24;

    public final double health;
    public final boolean alive;

    public final Upgrade upgrade;

    public final int tankNum;


    public Tank(double health, boolean alive, Upgrade upgrade, int tankNum) {
        this.health = health;
        this.alive = alive;
        this.upgrade = upgrade;
        this.tankNum = tankNum;
        MQTT_Publish health_publish = new MQTT_Publish("int","" + health,"tanks/tank_" + tankNum + "/health",GameMaster.client);
        MQTT_Publish alive_publish = new MQTT_Publish("boolean","" + alive,"tanks/tank_" + tankNum + "/alive",GameMaster.client);
        MQTT_Publish upgrade_publish = new MQTT_Publish("String","" + upgrade,"tanks/tank_" + tankNum + "/upgrade",GameMaster.client);
    }

    public Tank damageTank(double damage) {
        Tank returnTank;
        if(damage > 0) {
            if(this.alive){
                if(this.health - damage > 0){
                    returnTank = new Tank(this.health - damage, true, upgrade,this.tankNum);
                } else {
                    returnTank = new Tank(0,false , upgrade,this.tankNum);
                }
            } else {
                returnTank = new Tank( 0, false , upgrade,this.tankNum);
                System.out.println("Dead tank was damaged...");
            }
        } else {
            returnTank = new Tank(this.health - (-damage),this.alive , upgrade,this.tankNum);
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
