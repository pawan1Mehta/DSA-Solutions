class Solution {
    public boolean validWordSquare(List<String> words) {
        int n = words.size();

        for(int wordNum = 0; wordNum < n; wordNum++) {
            for(int charPos = 0; charPos < words.get(wordNum).length(); charPos++) {
                if(charPos >= n ||
                   wordNum >= words.get(charPos).length() ||
                   words.get(wordNum).charAt(charPos) != words.get(charPos).charAt(wordNum)) {
                    return false;
                }
            }
        }

        return true;
    }
}