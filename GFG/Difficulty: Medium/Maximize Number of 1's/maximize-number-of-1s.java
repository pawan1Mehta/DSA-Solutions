class Solution {
    public int maxOnes(int arr[], int k) {
        int n = arr.length;
        
        int maxConsecutiveNum1 = 0;
        int i = 0, j = 0;
        int zeros = 0;
        
        while(j < n) {
            zeros += 1 - arr[j];
            
            while(zeros > k) {
                zeros += arr[i] - 1;
                i++;
            }
            
            maxConsecutiveNum1 = Math.max(maxConsecutiveNum1, j - i + 1);
            
            j++;
        }
        
        return maxConsecutiveNum1;
    }
}