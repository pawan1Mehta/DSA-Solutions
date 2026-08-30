class Solution {
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        
        int lSubarray = 0;
        
        Map<Integer, Integer> idx = new HashMap<>();
        int sum = 0;
        
        for(int i = 0; i < n; i++) {
            if(arr[i] <= k) {
                sum--;
            } else {
                sum++;
            }
            
            if(sum > 0) {
                lSubarray = i + 1;
            }
            
            if(idx.containsKey(sum - 1)) {
                lSubarray = Math.max(lSubarray, i - idx.get(sum - 1));
            }
            
            if(!idx.containsKey(sum)) {
                idx.put(sum, i);
            }
        }
        
        return lSubarray;
    }
}