package nl.tudelft.semgroup4.util;

public class PlayerScorePair {
    
    private int score;
    private String name;
    
    public PlayerScorePair(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }
}
