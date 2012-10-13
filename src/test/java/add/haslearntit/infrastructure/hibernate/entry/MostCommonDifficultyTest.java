package add.haslearntit.infrastructure.hibernate.entry;

import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;

import java.util.Collections;

import org.junit.Test;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;

public class MostCommonDifficultyTest {
    private MostCommonDifficulty bean = new MostCommonDifficulty();

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnNullOnNoEntries() throws Exception {
        // given
        // nothing

        // when
        final Difficulty result = bean.selectMostCommonDifficultyFrom(Collections.EMPTY_LIST);

        // then
        assertThat(result).isNull();
    }

    @Test
    public void shouldReturnProperDifficulty() throws Exception {
        // given
        Entry easy1 = newEntry(Difficulty.EASY);
        Entry easy2 = newEntry(Difficulty.EASY);
        Entry hard = newEntry(Difficulty.HARD);

        // when
        Difficulty mostCommon = bean.selectMostCommonDifficultyFrom(asList(easy1, easy2, hard));

        // then
        assertThat(mostCommon).isEqualTo(Difficulty.EASY);
    }

    private Entry newEntry(Difficulty easy) {
        return new Entry("a", easy.toString(), "");
    }
}
