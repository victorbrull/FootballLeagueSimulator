import java.util.*;
import java.io.*;

public class interview {
    public static void main(String [] args) {
        String s = "My String";
        //reverse(s);
        int result = (int)Math.pow(2, 3);
        System.out.println(result);
    }

    private static void reverse(String s) {
        String reverseString = "";
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            reverseString = ch + reverseString;
        }
        System.out.println(reverseString);
    }
}
