
import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        HashMap<String, String> phoneBook = new HashMap<String, String>();

        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            String phone = in.nextLine();

            phoneBook.put(name, phone);
        }

        while (in.hasNextLine()) {
            String s = in.nextLine();

            if (phoneBook.containsKey(s)) {
                System.out.println(s + "=" + phoneBook.get(s));
            } else {
                System.out.println("Not found");
            }
        }

        in.close();
    }
}



