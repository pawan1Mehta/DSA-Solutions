// User function Template for Java

class Solution {
    
    static void radixSort(int arr[], int n) {
        int maxNum = getMaxNum(arr);
        
        for(int exp = 1; maxNum/exp > 0; exp = exp * 10) {
            countingSort(arr, exp);
        }
    }
    
    static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        
        int[] count = new int[10];
        
        for(int num : arr) {
            int targetNum = (num/exp) % 10;
            count[targetNum]++;
        }
        
        for(int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        int[] ans = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            int targetNum = (arr[i]/exp) % 10;
            ans[count[targetNum] - 1] = arr[i];
            count[targetNum]--;
        }
        
        for(int i = 0; i < n; i++) {
            arr[i] = ans[i];
        }
    }
    
    static int getMaxNum(int[] arr) {
        int n = arr.length;
        
        int maxNum = 0;
        
        for(int num : arr) {
            maxNum = Math.max(maxNum, num);
        }
        
        return maxNum;
    }
}
