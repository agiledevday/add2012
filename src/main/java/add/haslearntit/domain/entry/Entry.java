package add.haslearntit.domain.entry;

import java.util.UUID;

public class Entry {

    public static final int POINTS_FOR_UNKNOWN_DIFFICULTY = 0;

    private UUID id = UUID.randomUUID();

    private String name;
    private String difficultyLevel;
    private String timeSpent;

    private Entry() {
    }

    public Entry(String name, String difficulty, String timeSpent) {

        this();

        this.name = name;
        this.difficultyLevel = difficulty;
        this.timeSpent = timeSpent;
    }

    public String asMessage() {
        return String.format("A User has learnt %s, which was pretty %s, and it took him %s.", name, difficultyLevel, timeSpent);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Entry)) {
            return false;
        }

        Entry other = (Entry) obj;
        return id.equals(other.id);
    }

    @Override
    protected Entry clone() {
        Entry clone = new Entry();
        clone.id = id;
        clone.name = name;
        clone.timeSpent = timeSpent;
        clone.difficultyLevel = difficultyLevel;
        return clone;
    }

    public int getEarnedPoints() {
        try {
            Difficulty difficulty = Difficulty.valueOf(difficultyLevel);
            return difficulty.getLevel() * Integer.parseInt(timeSpent);
        } catch (IllegalArgumentException e) {
            return POINTS_FOR_UNKNOWN_DIFFICULTY;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", timeSpent='" + timeSpent + '\'' +
                '}';
    }
}
