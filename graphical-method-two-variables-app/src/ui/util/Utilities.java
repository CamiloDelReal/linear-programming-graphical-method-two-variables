package ui.util;

/**
 * Created with IntelliJ IDEA.
 * User: DarkLink
 * Date: 24/10/13
 * Time: 7:46
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {
    public static boolean isEmpty(String text){
        int i = 0;

        while(i < text.length() && text.charAt(i) == ' ')
            i++;

        return i == text.length() ? true : false;
    }
}
