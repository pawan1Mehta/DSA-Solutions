class Solution {

    private boolean check(int num, int t) {
        int prod = 1;
        while(num > 0) {
            prod = prod * (num%10);
            num /= 10;
        }
        return prod%t == 0 ? true : false;
    }

    public int smallestNumber(int n, int t) {
        while(!check(n, t)) {
            n++;
        }
        return n;
    }
}