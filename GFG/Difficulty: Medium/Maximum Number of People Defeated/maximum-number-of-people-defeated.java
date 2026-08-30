class Solution {
    int maxPeopleDefeated(int p) {
        int MAX_PEOPLE = (int) Math.sqrt(3 * 1_0000_0000);
        
        long[] peopleStrength = new long[MAX_PEOPLE];
        peopleStrength[0] = 1;
        for(int i = 1; i < MAX_PEOPLE; i++) {
            peopleStrength[i] = peopleStrength[i - 1] + (long) Math.pow(i + 1, 2);
        }
        
        int low = 0, high = MAX_PEOPLE - 1;
        int res = -1;
        
        while(low <= high) {
            int mid = (low + high)/2;
            
            if(p >= peopleStrength[mid]) {
                res = Math.max(res, mid + 1);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return res;
    }
};