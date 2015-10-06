package nl.tudelft.semgroup4.util;

public class HighscoreEntry {
    
    private long score;
    private String name;
    
    public HighscoreEntry(String name, long score) {
        this.name = name;
        this.score = score;
    }
    
    public long getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }
}
