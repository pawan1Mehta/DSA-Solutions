class Solution {
    public int sumDiffPairs(int[] arr, int k) {
        int n = arr.length;
        
        Arrays.sort(arr);
        
        int sum = 0;
        int i = n - 1;
        
        while(i >= 0) {
            if((i - 1) >= 0 && (arr[i] - arr[i - 1]) < k) {
                sum += arr[i] + arr[i - 1];
                i -= 2;
            } else {
                i--;
            }
        }
        
        return sum;
    }
}