package gamemaster;

import java.util.HashMap;
//I've decided that player 1 will always have tank 1, and player 2 tank 2 and so on.


public class WorldState {
    private final HashMap<Integer,TankPlayerPair> tankPlayerPairs;

    public WorldState(HashMap<Integer,TankPlayerPair> inPairs){
        tankPlayerPairs = (HashMap<Integer, TankPlayerPair>) inPairs.clone();
    }

    public WorldState(WorldState worldState){
        this(worldState.tankPlayerPairs);
    }

    public WorldState(){
        tankPlayerPairs = new HashMap<>();
    }


    public WorldState attack(int attackingPairNumber, int defendingPairNumber){
        WorldState returnState;
        HashMap<Integer,TankPlayerPair> returnMap = (HashMap<Integer, TankPlayerPair>) this.tankPlayerPairs.clone();

        //Set up data that will be needed
        TankPlayerPair defendingPair = tankPlayerPairs.get(defendingPairNumber);
        TankPlayerPair attackingPair = tankPlayerPairs.get(attackingPairNumber);

        if(attackingPair.tank.alive){
            double damage = attackingPair.tank.damage();
            //Update the defending tank with the damage that was done to it, and replace the object in the HashMap with the updated pair object
            Tank newDefendingTank = defendingPair.tank.damageTank(damage);
            TankPlayerPair newPair = defendingPair.updateByTank(newDefendingTank);
            System.out.println(newPair.toString());
            returnMap.replace(defendingPairNumber,newPair);

            //Update the attacking player's score and replace the attacking pair object with the updated version
            Player newAttackingPlayer = attackingPair.player.updateByScore(damage);
            returnMap.replace(attackingPairNumber,attackingPair.updateByPlayer(newAttackingPlayer));
            returnState = new WorldState(returnMap);
        } else {
            System.out.println("Dead tank tried to attack...");
            returnState = new WorldState(this);
        }
        return returnState;
    }

    public WorldState addPLayer(String playerName) {
        Player newPlayer = Player.createPlayer(playerName, tankPlayerPairs.size() + 1);
        System.out.println(newPlayer.number);
        TankPlayerPair newPair = new TankPlayerPair(newPlayer.tank,newPlayer);
        HashMap<Integer,TankPlayerPair> returnMap = this.tankPlayerPairs;
        returnMap.put(tankPlayerPairs.size() + 1, newPair);
        return new WorldState(returnMap);
    }

    public String toString(){
        String retStr = "";
        for(int i = 1; i < 5; i++){
            retStr = retStr + tankPlayerPairs.get(i).toString();
        }
        return retStr;
    }
}
