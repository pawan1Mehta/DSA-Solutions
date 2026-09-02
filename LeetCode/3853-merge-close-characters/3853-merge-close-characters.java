class Solution {
    public String mergeCharacters(String s, int k) {
        int n = s.length();

        StringBuilder resStr = new StringBuilder();
        Map<Character, Integer> idx = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            if(!idx.containsKey(s.charAt(i))) {
                int len = resStr.length();
                resStr.append(s.charAt(i));
                idx.put(s.charAt(i), len);
            } else {
                if((resStr.length() - idx.get(s.charAt(i))) > k) {
                    int len = resStr.length();
                    resStr.append(s.charAt(i));
                    idx.put(s.charAt(i), len);
                }
            }
        }

        return resStr.toString();
    }
}