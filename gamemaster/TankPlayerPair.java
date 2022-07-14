package gamemaster;

public class TankPlayerPair {
    public final Tank tank;
    public final Player player;

    public TankPlayerPair(Tank tank, Player player) {
        this.tank = tank;
        this.player = player;
    }

    public TankPlayerPair updateByTank(Tank newTank){
        return new TankPlayerPair(newTank,this.player);
    }

    public TankPlayerPair updateByPlayer(Player newPlayer){
        return new TankPlayerPair(this.tank,newPlayer);
    }

    public String toString(){
        return tank.toString();
    }
}
