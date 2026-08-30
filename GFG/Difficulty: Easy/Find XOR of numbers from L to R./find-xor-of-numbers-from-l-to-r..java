// User function Template for Java

class Solution {
    
    public static int computeXorOf1toN(int n) {
        if(n%4 == 0) {
            return n;
        }
        
        if(n%4 == 1) {
            return 1;
        }
        
        if(n%4 == 2) {
            return n + 1;
        }
        
        return 0;
    }
    
    public static int findXOR(int l, int r) {
        int L = computeXorOf1toN(l - 1);
        int R = computeXorOf1toN(r);
        
        return (L ^ R);
    }
}