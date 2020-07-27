package UnoGameGroup_9;

/**
 * This class represents the UNO card itself, beside functions and properties to
 * control it
 *
 * @author Group_9 (The Vipers)
 */
public class UnoCard extends Card {

    //declare the variables
    private COLORS color;
    private VALUE value;
    private SPECIAL_NUM specialValue;
    private boolean checkSpecial;

    /**
     * It constructs the non special card, also setting the color an the numeric
     * value
     *
     * @param value The value of the card
     * @param color The color of the card
     */
    public UnoCard(VALUE value, COLORS color) {

        this.color = color;
        this.value = value;
        this.specialValue = null;
        this.checkSpecial = false;
    }

    /**
     * It constructs the special card and sets the card to the special category
     *
     * @param specialValue The Special Values that are assigned inside the Enum
     */
    public UnoCard(SPECIAL_NUM specialValue) {

        this.color = COLORS.NONE;
        this.specialValue = specialValue;
        this.value = VALUE.ZERO;
        this.checkSpecial = true;
    }

    /**
     * this method simply returns the color of the card
     *
     * @return The color of card
     */
    public COLORS getColor() {

        return this.color;
    }

    /**
     * this method simply returns the value of the card
     *
     * @return The value of card
     */
    public int getValue() {

        return this.value.getNumVal();

    }

    /**
     * this method simply returns the numeric value of the card
     *
     * @return The numeric value of that card
     */
    public SPECIAL_NUM getSpecial_num() {

        return this.specialValue;

    }

    /**
     * This to string method is to Shape the card appearance using the console
     * to display it
     *
     * @return The string that shapes the card
     */
    public String toString() {

        String[] card = {" ----- ------", "|     |", "|     |", " ----- ------ "};
        String o = "";

        for (int q = 0; q < card.length; q++) {

            for (int t = 0; t < 1; t++) {

                if (!this.isSpecialCard()) {
                    if (q == 1) {

                        o = o + "|     " + this.getColor() + "    |" + " ";

                    } else if (q == 2) {

                        o = o + "|      " + this.getValue() + "     |" + " ";
                    } else {
                        o = o + card[q] + "     ";
                    }

                } else if (this.isSpecialCard()) {

                    if (q == 1) {

                        o = o + "|     " + "+" + this.getValue() + "      |" + " ";

                    } else {
                        o = o + card[q] + " ";
                    }

                }

            }

            o += "\n";

        }

        return o;

    }

    /**
     * This method checks if the card special or not
     *
     * @return A Boolean value represents a special checker
     */
    public boolean isSpecialCard() {

        if (checkSpecial) {
            return true;
        }

        return false;
    }

}
