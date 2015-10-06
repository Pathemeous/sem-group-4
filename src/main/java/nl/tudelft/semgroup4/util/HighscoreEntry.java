package nl.tudelft.semgroup4.util;

public class HighscoreEntry {
    
    private final long score;
    private final String name;
    
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
