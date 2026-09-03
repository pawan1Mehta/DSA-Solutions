class Solution {
    public long numberOfSubstrings(String s) {
        int n = s.length();

        long subStrCount = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for(char ch : s.toCharArray()) {
            subStrCount += freq.getOrDefault(ch, 0);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        return subStrCount + n;
    }
}