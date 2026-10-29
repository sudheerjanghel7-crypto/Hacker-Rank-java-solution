
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Deque<Integer> deque = new ArrayDeque<Integer>();
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();

        int maxUnique = 0;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();

            deque.addLast(num);

            if (freq.containsKey(num)) {
                freq.put(num, freq.get(num) + 1);
            } else {
                freq.put(num, 1);
            }

            if (deque.size() > m) {
                int removed = deque.removeFirst();
                int count = freq.get(removed);

                if (count == 1) {
                    freq.remove(removed);
                } else {
                    freq.put(removed, count - 1);
                }
            }

            if (deque.size() == m) {
                maxUnique = Math.max(maxUnique, freq.size());
            }
        }

        System.out.println(maxUnique);
        in.close();
    }
}


