class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int decr=1;
        int incr=1;
        int max=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]>nums[i-1]) {
                incr++;
                decr=1;
            } else if (nums[i]<nums[i-1]) {
                decr++;
                incr=1;
            } else {
                decr = 1;
                incr = 1;
            }
            max = Math.max(max,Math.max(incr,decr));
        }
        return max;
    }
}