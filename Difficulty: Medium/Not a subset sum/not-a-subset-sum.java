class Solution {
    public int findSmallest(int[] arr) {
        Arrays.sort(arr);
        
        int currSum = 0;
        
        for(int num : arr) {
            if(num > (currSum + 1)) {
                return currSum + 1;
            }
            currSum += num;
        }
        
        return currSum  + 1;
    }
}