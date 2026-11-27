class Solution {
    public int maximumSaleItems(int[][] items,int budget) {
        int n=items.length;
        int[] free=new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j&&items[j][0]%items[i][0]==0){
                    free[i]++;
                }
            }
        }
        int[] dp=new int[budget+1];
        for(int i=0;i<n;i++){
            int p=items[i][1];
            int f=free[i];
            int[] activated=new int[budget+1];
            java.util.Arrays.fill(activated,-1000000);
            int[] next=dp.clone();
            for(int b=p;b<=budget;b++){
                activated[b]=Math.max(dp[b-p]+1+f,activated[b-p]+1);
                next[b]=Math.max(next[b],activated[b]);
            }
            dp=next;
        }
        return dp[budget];
    }
}