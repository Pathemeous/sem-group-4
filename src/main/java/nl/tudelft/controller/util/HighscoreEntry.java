package nl.tudelft.controller.util;

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

    @Override
    public boolean equals(Object obj) {

        if (obj != null && getClass().equals(obj.getClass())) {
            HighscoreEntry that = (HighscoreEntry) obj;
            return this.score == that.score
                    && this.name.equals(that.name);
        }

        return super.equals(obj);
    }
}
