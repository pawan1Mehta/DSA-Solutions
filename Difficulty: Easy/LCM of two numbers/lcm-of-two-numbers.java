class Solution {
    
    public int gcd(int a, int b) {
        return a == 0 ? b : gcd(b%a, a);
    }
    
    public int lcm(int a, int b) {
        return (a * b)/gcd(a, b);
    }
}