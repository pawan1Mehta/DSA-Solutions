// User function Template for Java
class Solution {
    static int setBit(int n) {
        for(int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if((n & mask) == 0) {
                n = n | mask;
                return n;
            }
        }
        return n;
    }
}