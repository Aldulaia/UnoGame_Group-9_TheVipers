package UnoGameGroup_9;

/**
 * This class is an Enum that represents the Special Values
 *
 * @author Group_9 (The Vipers)
 */
public enum SPECIAL_NUM {
    PLUS_TWO(2),
    PLUS_FOUR(4);

    int specNum;
    boolean special;

    private SPECIAL_NUM(int n) {
        this.specNum = n;
    }

}
