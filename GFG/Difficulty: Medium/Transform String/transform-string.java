class Solution {
    int transform(String s1, String s2) {
        int n = s1.length();
        
        if(n != s2.length()) {
            return -1;
        }
        
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : s1.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for(char ch : s2.toCharArray()) {
            if(freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) - 1);
            }
        }
        
        for(int val : freq.values()) {
            if(val != 0) {
                return -1;
            }
        }
        
        int count = 0;
        int i = n - 1, j = n - 1;
        
        while(j >= 0 && i >= 0) {
            while(i >= 0 && s1.charAt(i) != s2.charAt(j)) {
                count++;
                i--;
            }
            
            if(i >= 0) {
                i--;
                j--;
            }
        }
        
        return count;
    }
}