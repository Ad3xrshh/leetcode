class Solution {
    public double myPow(double x, int n) {
        long power = n;
        if (power < 0) power = -power;
        double ans = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                ans *= x;
            }
            x *= x;
            power /= 2;
        }
        if (n < 0) ans = 1 / ans;
        return ans;
    }
}
