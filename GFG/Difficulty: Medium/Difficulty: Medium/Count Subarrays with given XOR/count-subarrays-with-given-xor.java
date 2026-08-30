class Solution {
    
    private int getRequiredNum(int num, int targetNum) {
        int reqNum = 0;

        for(int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            
            if((targetNum & mask) != 0) {
                if((mask & num) == 0) {
                    reqNum = reqNum | (1 << i);
                }
            } else {
                if((mask & num) != 0) {
                    reqNum = reqNum | (1 << i);
                }
            }
        }
        
        return reqNum;
    }
    
    public long subarrayXor(int arr[], int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        freq.put(0, 1);
        
        long count = 0;
        
        int xorNum = 0;
        for(int num : arr) {
            xorNum = xorNum ^ num;
            
            int requiredNum = getRequiredNum(xorNum, k);
            
            if(freq.containsKey(requiredNum)) {
                count += freq.get(requiredNum);
            }
            
            freq.put(xorNum, freq.getOrDefault(xorNum, 0) + 1);
        }
        
        return count;
    }
}