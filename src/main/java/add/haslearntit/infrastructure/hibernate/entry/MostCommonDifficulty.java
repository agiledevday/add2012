package add.haslearntit.infrastructure.hibernate.entry;

import java.util.List;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;

public class MostCommonDifficulty {

    public Difficulty selectMostCommonDifficultyFrom(List<Entry> loaded) {
        if (loaded.isEmpty()) {
            return null;
        } else {
            int countHard = countEntries(loaded, Difficulty.HARD);
            int countEasy = countEntries(loaded, Difficulty.EASY);
            int countModerate = countEntries(loaded, Difficulty.MODERATE);

            if (isMax(countHard, countEasy, countModerate)) {
                return Difficulty.HARD;
            } else if (isMax(countModerate, countEasy, countHard)) {
                return Difficulty.MODERATE;
            } else {
                return Difficulty.EASY;
            }
        }
    }

    private int countEntries(List<Entry> loaded, Difficulty difficulty) {
        int result = 0;

        for (final Entry entry : loaded) {
            if (difficulty.equals(entry.getDifficulty())) {
                ++result;
            }
        }

        return result;
    }

    private boolean isMax(int candidate, int other, int other2) {
        return (candidate > other2) && (candidate > other);
    }
}