import java.io.*;
import java.util.*;
import java.security.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(s.getBytes("UTF-8"));

        for (byte b : hash) {
            System.out.printf("%02x", b & 0xff);
        }

        System.out.println();
        sc.close();
    }
}