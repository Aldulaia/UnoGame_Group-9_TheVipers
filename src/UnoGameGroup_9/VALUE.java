package UnoGameGroup_9;

/**
 * This class represents the values of the UNO card as an object stored in Enum
 *
 * @author Group_9 (The Vipers)
 */
public enum VALUE {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    ZERO(0);

    int numVal;

    private VALUE(int n) {
        this.numVal = n;
    }

    /**
     * @return the numVal
     */
    public int getNumVal() {
        return numVal;
    }

    /**
     * @param numVal the numVal to set
     */
    public void setNumVal(int numVal) {
        this.numVal = numVal;
    }

}
