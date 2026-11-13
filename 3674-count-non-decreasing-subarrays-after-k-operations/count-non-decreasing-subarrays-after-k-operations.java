
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        Deque<int[]> dq = new ArrayDeque<>();
        long ans = 0;
        long cost = 0;
        int j = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 1;
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] last = dq.pollLast();
                cost += (long)(num - last[0]) * last[1];
                count += last[1];
            }
            dq.offerLast(new int[]{num, count});

            while (cost > k) {
                int[] first = dq.peekFirst();
                cost -= first[0] - nums[j];
                first[1]--;
                if (first[1] == 0) {
                    dq.pollFirst();
                }
                j--;
            }
            ans += j-i+ 1;
        }
        return ans;
    }
}
