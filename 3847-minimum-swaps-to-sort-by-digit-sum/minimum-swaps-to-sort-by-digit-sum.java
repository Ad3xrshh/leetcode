
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();

        Arrays.sort(sorted);

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = nums[i];
        }

        Arrays.sort(order, (a, b) -> {
            int sa = digitSum(a);
            int sb = digitSum(b);

            if (sa != sb) {
                return Integer.compare(sa, sb);
            }
            return Integer.compare(a, b);
        });

        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(nums[i], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                j = index.get(order[j]);
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
