/**
 * This class is used to compare strings with weak hashes.
 * Hashes are calculated as sum of all chars in string.
 * Task allows to recalculate hash of next string very fast because of
 * fact, that strings are different only in one char.
 */
public class SequentialSampleMatcher {

    private static final int Q = 1 << 31 - 1; //2^31-1

    private final String sample;

    private final int sampleHash;

    private final int length;

    private char[] values;

    /**
     * Position of the oldest (first in given sequence) character.
     * Also, position for next new character.
     */
    private int position;

    private int hash = 0;

    public boolean check(char ch) {
        hash = (hash + Q - (values[position] << (length - 1)) % Q) % Q;
        values[position] = ch;
        hash = ((hash << 1) + values[position]) % Q;
        nextPosition();
        return ((hash == sampleHash) && isReallyEqual());
    }

    /**
     * Increases position or sets to 0 if goes out of range.
     */
    private int nextPosition() {
        position = (position + 1) % length;
        return position;
    }

    private boolean isReallyEqual() {
        for (int i = 0, j = position; i < sample.length(); i++, j = (j + 1) % length) {
            if (sample.charAt(i) != values[j]) {
                return false;
            }
        }
        return true;
    }

    public static int hashCode(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++) {
            hash = ((hash << 1) + string.charAt(i)) % Q;
        }
        return hash;
    }

    public SequentialSampleMatcher(String sample) {
        this.sample = sample;
        this.length = sample.length();
        this.sampleHash = hashCode(sample);
        this.values = new char[length];
    }
}
