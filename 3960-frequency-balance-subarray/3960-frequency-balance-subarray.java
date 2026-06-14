class Solution {
    public int getLength(int[] nums) {
        int n = nums.length;

        int maxLen = 0;

        for(int i = 0; i < n; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            int maxFreq = 0;
            
            for(int j = i; j < n; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
                maxFreq = Math.max(maxFreq, freq.get(nums[j]));

                if(freq.size() == 1) {
                    maxLen = Math.max(maxLen, j - i + 1);
                    continue;
                }

                if(maxFreq%2 != 0) {
                    continue;
                }

                boolean isTrue = true;
                boolean isExist = false;
                
                for(int val : freq.values()) {
                    if(val == maxFreq) continue;
                    if(val != (maxFreq/2)) {
                        isTrue = false;
                        break;
                    }
                    isExist = true;
                }
                
                if(isTrue && isExist) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}