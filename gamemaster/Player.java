package gamemaster;

public class Player {
    private static int playerItter = 0;

    public final String name;
    public final int number;
    public final double score;
    public final Tank tank;

    private Player(String name, int number, double score, Tank tank) {
        this.name = name;
        this.number = number;
        this.score = score;
        this.tank = tank;
    }

    public Player updateByScore(double scoreToAdd){
        return new Player(this.name,this.number,this.score + Math.abs(scoreToAdd),this.tank);
    }


    public static Player createPlayer(String playerName, int playerNumber){
        return new Player(playerName,playerNumber,0,new Tank(100,true, Upgrade.defaultUpgrade(),playerNumber));
    }



    public String toString(){
        return "--Player Details--\n\tName: " + name + "\n\tPlayer Number: " + number + "\n\tPlayer Score: " + score + "\n\t" + tank.toString();
    }
}


