class Solution {
    public int subarrayXor(int[] arr) {
        int n = arr.length;
        
        Map<Integer, Integer> freq = new HashMap<>();
        
        for(int i = 1; i <= n; i++) {
            int currIntFreq = (n - i + 1) * (i);
            freq.put(arr[i - 1], freq.getOrDefault(arr[i - 1], 0) + currIntFreq);
        }
        
        int num = 0;
        
        for(Map.Entry<Integer, Integer> data : freq.entrySet()) {
            if(data.getValue()%2 != 0) {
                num = num ^ data.getKey();
            }
        }
        
        return num;
    }
}