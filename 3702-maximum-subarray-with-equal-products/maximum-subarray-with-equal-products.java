class Solution {
    int gcd(int a,int b){
        if (a==0)
            return b;
        return gcd(b%a,a);
    }

    int lcm(int a,int b){
        return (a/gcd(a,b))*b;
    }

    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i=0;i<n;i++){
            int product = 1;
            int gcd = 0;
            int lcm = 1;
            for (int j=i;j<n;j++){
                product *= nums[j];
                gcd = gcd(gcd,nums[j]);
                lcm = lcm(lcm,nums[j]);
                
                if (product == lcm*gcd){
                    ans = Math.max(ans,j-i+1);
                }
            }
        }

        return ans;
    }
}