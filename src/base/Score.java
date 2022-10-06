package base;

/**
 * Score object for player 1 and player 2
 * @author Kirils Turkins
 * @category Pong Component
 */

public class Score {
    
    private int scoreP1 = 0;
    private int scoreP2 = 0;

    // Increace score for selected player
    protected void incScore(int player){

        if(player == 1){
            scoreP1 ++;
        } else {
            scoreP2 ++;
        }
    }

    // return score from player 1
    public int getScoreP1(){
        return scoreP1;
    }

    // return score from player 2
    public int getScoreP2(){
        return scoreP2;
    }
}
