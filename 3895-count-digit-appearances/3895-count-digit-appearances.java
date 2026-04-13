class Solution {
    private int countFreq(int num, int digit) {
        int count = 0;
        
        while(num > 0) {
            int d = num % 10;
            
            if(d == digit) {
                count++;
            }

            num = num/10;
        }
        
        return count;
    }
    
    public int countDigitOccurrences(int[] nums, int digit) {
        int count = 0;

        for(int num : nums) {
            count += countFreq(num, digit);
        }

        return count;
    }
}