class Solution {
    public int countOdds(int low, int high) {
        int count = 0;

        if(low%2 == 0) {
            low++;
        } else {
            count++;
            low += 2;
        }

        if(low > high) {
            return count;
        }

        int rem = ((high - low)/2) + 1;
        
        return count + rem;
    }
}
/**

    count = 1

    low = 9
    high = 10

    

 */