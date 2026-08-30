class Solution {
    
    public int countSubstrWithAtleastK(String s, int k) {
        int n = s.length();
        
        int substrings = 0;
        
        Map<Character, Integer> freq = new HashMap<>();
        int i = 0, j = 0;
        
        while(j < n) {
            freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);
            
            while(freq.size() > k) {
                freq.put(s.charAt(i), freq.get(s.charAt(i)) - 1);
                
                if(freq.get(s.charAt(i)) == 0) {
                    freq.remove(s.charAt(i));
                }
                
                i++;
            }
            
            substrings += (j - i + 1);
            
            j++;
        }
        
        return substrings;
    }
    
    public int countSubstr(String s, int k) {
        return countSubstrWithAtleastK(s, k) - countSubstrWithAtleastK(s, k-1);
    }
}