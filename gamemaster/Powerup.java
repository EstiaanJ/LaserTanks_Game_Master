package gamemaster;

public class Powerup {

    public Powerup(Powerup powerup){
        this();
    }

    public Powerup(){

    }

    public static Powerup defaultUpgrade(){
        return new Powerup();
    }
}
