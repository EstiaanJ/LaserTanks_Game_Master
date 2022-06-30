package gamemaster;

import java.util.HashMap;
//I've decided that player 1 will always have tank 1, and player 2 tank 2 and so on.


public class WorldState {
    private HashMap<Integer,TankPlayerPair> tankPlayerPairs = new HashMap<>();



    public void attack(int attackingPairNumber, int defendingPairNumber){
        //Set up date that will be needed
        TankPlayerPair defendingPair = tankPlayerPairs.get(defendingPairNumber);
        TankPlayerPair attackingPair = tankPlayerPairs.get(attackingPairNumber);

        if(attackingPair.tank.alive){
            double damage = attackingPair.tank.damage();
            //Update the defending tank with the damage that was done to it, and replace the object in the HashMap with the updated pair object
            Tank newDefendingTank = defendingPair.tank.damageTank(damage);
            TankPlayerPair newPair = defendingPair.updateByTank(newDefendingTank);
            tankPlayerPairs.replace(defendingPairNumber,newPair);

            //Update the attacking player's score and replace the attacking pair object with the updated version
            Player newAttackingPlayer = attackingPair.player.updateByScore(damage);
            tankPlayerPairs.replace(attackingPairNumber,attackingPair.updateByPlayer(newAttackingPlayer));
        } else {
            System.out.println("Dead tank tried to attack...");
        }
    }

    public void addPLayer(String playerName) {
        Player newPlayer = Player.createPlayer(playerName, tankPlayerPairs.size() + 1);
        System.out.println(newPlayer.number);
        TankPlayerPair newPair = new TankPlayerPair(newPlayer.tank,newPlayer);
        tankPlayerPairs.put(tankPlayerPairs.size() + 1, newPair);
    }
}
