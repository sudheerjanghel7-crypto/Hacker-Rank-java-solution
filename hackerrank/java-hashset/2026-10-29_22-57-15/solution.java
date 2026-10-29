import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int t = Integer.parseInt(br.readLine().trim());
        Set<List<String>> set = new HashSet<>();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String[] p = br.readLine().trim().split("\\s+");
            set.add(Arrays.asList(p[0], p[1]));
            out.append(set.size()).append('\n');
        }

        System.out.print(out);
    }
}
