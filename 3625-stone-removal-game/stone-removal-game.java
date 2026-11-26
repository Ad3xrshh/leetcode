class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        int count = 0;
        while (n>=remove){
            n= n-remove;
            remove--;
            count++;
        }
        if (count%2==0){
            return false;
        } else {
            return true;
        }
    }
}