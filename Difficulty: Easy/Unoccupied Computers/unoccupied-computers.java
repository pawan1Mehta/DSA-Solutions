class Solution {
    public int solve(int n, String s) {
        Set<Character> seen = new HashSet<>();
        Set<Character> unAssignedSt = new HashSet<>();
        
        int unAssignedCustomers = 0;
        
        for(char ch : s.toCharArray()) {
            if(unAssignedSt.contains(ch)) {
                continue;
            }
            
            if(seen.contains(ch)) {
                n++;
            } else {
                if(n == 0) {
                    unAssignedCustomers++;
                    unAssignedSt.add(ch);
                } else {
                    seen.add(ch);
                    n--;
                }
            }
        }
        
        return unAssignedCustomers;
    }
}
