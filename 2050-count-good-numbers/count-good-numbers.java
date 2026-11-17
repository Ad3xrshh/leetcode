class Solution {
    public int countGoodNumbers(long n) {
        long mod = 1000000007;
        long even = (n + 1) / 2;
        long odd = n / 2;
        long a = 1, b = 1;
        long x = 5, y = 4;
        while (even > 0) {
            if (even % 2 == 1) a = (a * x) % mod;
            x = (x * x) % mod;
            even /= 2;
        }
        while (odd > 0) {
            if (odd % 2 == 1) b = (b * y) % mod;
            y = (y * y) % mod;
            odd /= 2;
        }
        return (int) ((a * b) % mod);
    }
}
