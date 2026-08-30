class Solution {
    public int totalElements(int[] arr) {
        int n = arr.length;
        
        int maxLen = 0;
        
        Map<Integer, Integer> mp = new HashMap<>();
        int i = 0, j = 0;
        
        while(j < n) {
            mp.put(arr[j], mp.getOrDefault(arr[j], 0) + 1);
            
            while(mp.size() > 2) {
                mp.put(arr[i], mp.get(arr[i]) - 1);
                
                if(mp.get(arr[i]) == 0) {
                    mp.remove(arr[i]);    
                }
                
                i++;
            }
            
            maxLen = Math.max(maxLen, j - i + 1);
            
            j++;
        }
        
        return maxLen;
    }
}