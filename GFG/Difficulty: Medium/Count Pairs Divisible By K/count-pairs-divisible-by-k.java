class Solution {
    public int countKdivPairs(int[] arr, int k) {
        int pairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : arr) {
            int rem = num%k;
            int needRem = (rem == 0 ? 0 : k - rem);
            
            if(freq.containsKey(needRem)) {
                pairs += freq.get(needRem);
            }
            
            freq.put(rem, freq.getOrDefault(rem, 0) + 1);
        }
        
        return pairs;
    }
}