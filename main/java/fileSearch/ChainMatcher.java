package fileSearch;

/**
 * This class is used to compare strings by polynomial hashes.
 * Task allows to recalculate hash of next string very fast because of
 * fact, that strings are different only in one char.
 * Characters which has to be compared with sample string are
 * stored in custom cyclic queue.
 */
public class ChainMatcher {

    /**
     * Plain value 2^31-1
     */
    private static final int Q = 1 << 31 - 1;

    private final String sample;

    private final int sampleHash;

    private final int length;

    /**
     * Custom char queue (with fixed length == sample length).
     */
    private char[] values;

    /**
     * Position of the oldest (first in given sequence) character.
     * Also, position for next new character.
     * Queue pointer
     */
    private int position;

    /**
     * Polynomial hash of sequence in queue
     */
    private int hash = 0;

    public boolean check(char ch) {
        hash = (hash + Q - (values[position] << (length - 1)) % Q) % Q;
        values[position] = ch;
        hash = ((hash << 1) + values[position]) % Q;
        nextPosition();
        return ((hash == sampleHash) && isReallyEqual());
    }

    /**
     * @return true, if sequence in queue is equal to sample string
     * false, if not
     */
    private boolean isReallyEqual() {
        for (int i = 0, j = position; i < sample.length(); i++, j = (j + 1) % length) {
            if (sample.charAt(i) != values[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Increases position or sets to 0 if goes out of range.
     */
    private int nextPosition() {
        position = (position + 1) % length;
        return position;
    }

    /**
     * @param string
     * @return polynomial hash of given string
     */
    public static int hashCode(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++) {
            hash = ((hash << 1) + string.charAt(i)) % Q;
        }
        return hash;
    }

    public ChainMatcher(String sample) {
        this.sample = sample;
        this.length = sample.length();
        this.sampleHash = hashCode(sample);
        this.values = new char[length];
    }
}
