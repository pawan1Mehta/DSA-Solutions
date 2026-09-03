class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        Map<Character, Integer> evenIdxChars = new HashMap<>();
        Map<Character, Integer> oddIdxChars = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            char ch = s1.charAt(i);
            if(i%2 == 0) {
                evenIdxChars.put(ch, evenIdxChars.getOrDefault(ch, 0) + 1);
            } else {
                oddIdxChars.put(ch, oddIdxChars.getOrDefault(ch, 0) + 1);
            }
        }

        for(int i = 0; i < n; i++) {
            char ch = s2.charAt(i);
            if(i%2 == 0) {
                if(!evenIdxChars.containsKey(ch)) {
                    return false;
                }

                evenIdxChars.put(ch, evenIdxChars.get(ch) - 1);
                if(evenIdxChars.get(ch) == 0) {
                    evenIdxChars.remove(ch);
                }
            } else {
                if(!oddIdxChars.containsKey(ch)) {
                    return false;
                }
                
                oddIdxChars.put(ch, oddIdxChars.get(ch) - 1);
                if(oddIdxChars.get(ch) == 0) {
                    oddIdxChars.remove(ch);
                }
            }
        }

        return true;
    }
}