class Solution {
    public String makeAntiPalindrome(String s) {
        int n = s.length();

        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        StringBuilder resStr = new StringBuilder();
        for(int i = 0; i < n/2; i++) {
            resStr.append(chars[i]);
        }

        Map<Character, Integer> mp = new TreeMap<>();
        for(int i = n/2; i < n; i++) {
            mp.put(chars[i], mp.getOrDefault(chars[i], 0) + 1);
        }

        int i = n/2 - 1;
        while(i >= 0) {
            char ch = '#';
            for(Map.Entry<Character, Integer> data : mp.entrySet()) {
                if(chars[i] != data.getKey()) {
                    ch = data.getKey();
                    break;
                }
            }

            if(ch == '#') {
                return "-1";
            }

            mp.put(ch, mp.get(ch) - 1);
            if(mp.get(ch) == 0) {
                mp.remove(ch);
            }

            resStr.append(ch);

            i--;
        }

        return resStr.toString();
    }
}