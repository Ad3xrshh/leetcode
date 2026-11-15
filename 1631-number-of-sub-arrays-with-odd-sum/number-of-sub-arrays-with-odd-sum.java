class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long odd = 0;
        long even = 1;
        long count = 0;
        int sum = 0;
        for (int i=0;i<arr.length;i++){
            sum = (sum+arr[i]) % 2;
            if (sum==1){
                count = (count+even)%mod;
                odd++;
            } else {
                count = (count+odd)%mod;
                even++;
            }
        }
        
        return (int)count;
    }
}
