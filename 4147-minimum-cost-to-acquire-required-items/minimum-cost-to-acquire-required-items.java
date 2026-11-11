
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long C1 = (long)need1*cost1+(long)need2*cost2;
        long C2 = (long)Math.min(need1,need2)*costBoth;
        if (need1>need2) {
            C2 += (long)(need1-need2)*Math.min(cost1,costBoth);
        } else {
            C2 += (long)(need2-need1)*Math.min(cost2,costBoth);
        }
        long C3 = (long)Math.max(need1,need2)*costBoth;
        return Math.min(C1,Math.min(C2,C3));
    }
}




