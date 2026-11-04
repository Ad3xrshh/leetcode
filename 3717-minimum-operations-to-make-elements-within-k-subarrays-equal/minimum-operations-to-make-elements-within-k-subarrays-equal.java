class Solution {

    class SlidingWindow {
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        long sumLower = 0;
        long sumUpper = 0;

        int sizeLower = 0;
        int sizeUpper = 0;

        void add(int num) {
            if (lower.isEmpty() || num <= lower.lastKey()) {
                lower.put(num, lower.getOrDefault(num, 0) + 1);
                sumLower += num;
                sizeLower++;
            } else {
                upper.put(num, upper.getOrDefault(num, 0) + 1);
                sumUpper += num;
                sizeUpper++;
            }

            balance();
        }

        void remove(int num) {
            if (lower.containsKey(num)) {
                removeFromMap(lower, num);
                sumLower -= num;
                sizeLower--;
            } else {
                removeFromMap(upper, num);
                sumUpper -= num;
                sizeUpper--;
            }

            balance();
        }

        void removeFromMap(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        void balance() {
            int target = (sizeLower + sizeUpper + 1) / 2;

            while (sizeLower > target) {
                int num = lower.lastKey();

                removeFromMap(lower, num);
                sumLower -= num;
                sizeLower--;

                upper.put(num, upper.getOrDefault(num, 0) + 1);
                sumUpper += num;
                sizeUpper++;
            }

            while (sizeLower < target) {
                int num = upper.firstKey();

                removeFromMap(upper, num);
                sumUpper -= num;
                sizeUpper--;

                lower.put(num, lower.getOrDefault(num, 0) + 1);
                sumLower += num;
                sizeLower++;
            }
        }

        long getCost() {
            long median = lower.lastKey();

            long leftCost = median * sizeLower - sumLower;
            long rightCost = sumUpper - median * sizeUpper;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        SlidingWindow window = new SlidingWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = 1; i <= n - x; i++) {
            window.remove(nums[i - 1]);
            window.add(nums[i + x - 1]);

            cost[i] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 2;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {

                    long take = dp[i - x][j - 1] + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }
}