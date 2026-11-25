class Solution {
    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;
    static final long BASE1 = 1000003L;
    static final long BASE2 = 1000033L;

    long[] p1,p2,h1,h2;
    int n;
    void build(int[] nums){
        n=nums.length;
        p1=new long[n+1];
        p2=new long[n+1];
        h1=new long[n+1];
        h2=new long[n+1];

        p1[0]=p2[0]=1;
        for(int i=0;i<n;i++){
            long v1=((nums[i]%MOD1)+MOD1)%MOD1+1;
            long v2=((nums[i]%MOD2)+MOD2)%MOD2+1;
            p1[i+1]=p1[i]*BASE1%MOD1;
            p2[i+1]=p2[i]*BASE2%MOD2;
            h1[i+1]=(h1[i]*BASE1+v1)%MOD1;
            h2[i+1]=(h2[i]*BASE2+v2)%MOD2;
        }
    }
    long getHash(int l,int r){
        int len=r-l+1;
        long a=(h1[r+1]-h1[l]*p1[len]%MOD1+MOD1)%MOD1;
        long b=(h2[r+1]-h2[l]*p2[len]%MOD2+MOD2)%MOD2;
        return (a<<32)|b;
    }
    boolean check(int k){
        HashMap<Long,Integer> map=new HashMap<>();

        for(int i=0;i+k<=n;i++){
            long hash=getHash(i,i+k-1);
            map.put(hash,map.getOrDefault(hash,0)+1);
        }
        for(int count:map.values()){
            if(count==1)
                return true;
        }
        return false;
    }
    public int smallestUniqueSubarray(int[] nums){
        build(nums);
        int low=1,high=n,ans=n;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(check(mid)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
}