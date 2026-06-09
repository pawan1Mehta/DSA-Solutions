class Solution {
    public boolean canSeatAllPeople(int k, int[] seats) {
        int n = seats.length;
        
        for(int i = 0; i < n; i++) {
            int prev = (i == 0 ? 0 : seats[i - 1]);
            int curr = seats[i];
            int next = ((i == n - 1) ? 0 : seats[i + 1]);
            
            if(prev == 0 && curr == 0 && next == 0) {
                seats[i] = 1;
                k--;
            }
            
            if(k == 0) {
                return true;
            }
        }
        
        return false;
    }
}