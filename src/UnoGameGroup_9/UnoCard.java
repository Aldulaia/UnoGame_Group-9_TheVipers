package UnoGameGroup_9;

public class UnoCard extends Card {

    private COLORS color;
    private VALUE value;
    private SPECIAL_NUM specialValue;
    private boolean checkSpecial;

    public UnoCard(VALUE value, COLORS color) {

        /*
		 * constructs a non speicla card
		 * sets the color and numerical value
		 * sets it to the normal card category
         */
        this.color = color;
        this.value = value;
        this.specialValue = null;
        this.checkSpecial = false;
    }

    public UnoCard(SPECIAL_NUM specialValue) { // constructor for special cards like +4 and +2	

        /*
		 * assigns special value to the card
		 * sets the card to special category
         */
        this.color = COLORS.NONE;
        this.specialValue = specialValue;
        this.value = VALUE.ZERO;
        this.checkSpecial = true;
    }

    public COLORS getColor() {
        /*
		 * returns color of the card
         */

        return this.color;
    }

    public int getValue() {

        /*
		 * returns numerical value of the card
         */
        return this.value.getNumVal();

    }

    public SPECIAL_NUM getSpecial_num() {

        /*
		 * returns numerical value of the card
         */
        return this.specialValue;

    }

    public String toString() {

        /*
		 * prints the card.
		 * prints [ color - card value ] if the card is not a special card
		 * prints [ + value ] if it is a special card 
         */
//		if(!special) {
//			return "[ "+this.color+"-"+this.value+" ]";
//		}
//		
//		else if(special){
//			
//			return "[ "+"+"+this.specialValue+" ]"; 
//		}
//		return null;
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

    public boolean isSpecialCard() {

        /*
		 * returns true if card is a special card
		 * returns false if card is not a special card
         */
        if (checkSpecial) {
            return true;
        }

        return false;
    }

}
