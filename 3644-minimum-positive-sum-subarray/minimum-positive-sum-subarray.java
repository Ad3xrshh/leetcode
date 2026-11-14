class Solution {
    public int minimumSumSubarray(List<Integer> nums,int l,int r) {
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<nums.size();i++){
            int sum=0;
            for(int j=i;j<nums.size() && j-i+1<=r;j++){
                sum+=nums.get(j);
                if(j-i+1>=l && sum>0){
                    ans=Math.min(ans,sum);
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return ans;
        }
    }
}
