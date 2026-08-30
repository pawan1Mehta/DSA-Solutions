class Solution {
    public int smallestSubstring(String s) {
        int n = s.length();
        
        Map<Character, Integer> freq = new HashMap<>();
        
        int minLen = Integer.MAX_VALUE;
        int i = 0, j = 0;
        
        while(j < n) {
            freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);
            
            while(freq.size() == 3) {
                minLen = Math.min(minLen, j - i + 1);
                
                freq.put(s.charAt(i), freq.get(s.charAt(i)) - 1);
                
                if(freq.get(s.charAt(i)) == 0) {
                    freq.remove(s.charAt(i));
                }
                
                i++;
            }
            
            j++;
        }
        
        return minLen == Integer.MAX_VALUE ? - 1 : minLen;
    }
};
