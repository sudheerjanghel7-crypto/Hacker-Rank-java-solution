
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);

        for (int i = 0; i < m; i++) {
            String operation = sc.next();
            int a = sc.nextInt();
            int b = sc.nextInt();

            BitSet first = (a == 1) ? b1 : b2;
            BitSet second = (b == 1) ? b1 : b2;

            switch (operation) {
                case "AND":
                    first.and(second);
                    break;

                case "OR":
                    first.or(second);
                    break;

                case "XOR":
                    first.xor(second);
                    break;

                case "FLIP":
                    first.flip(b);
                    break;

                case "SET":
                    first.set(b);
                    break;
            }

            System.out.println(b1.cardinality() + " " + b2.cardinality());
        }

        sc.close();
    }
}
