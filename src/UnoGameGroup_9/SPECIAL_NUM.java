/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnoGameGroup_9;

/**
 *
 * @author QQ
 */
public enum SPECIAL_NUM {
    PLUS_TWO(2),
    PLUS_FOUR(4);

    int specNum;
    boolean special;

    private SPECIAL_NUM(int n) {
        this.specNum = n;
    }
//
//    public boolean  isSpecial_num() {
//        if (special) {
//           special = true;
//        }
//         return special;
//    }

}
