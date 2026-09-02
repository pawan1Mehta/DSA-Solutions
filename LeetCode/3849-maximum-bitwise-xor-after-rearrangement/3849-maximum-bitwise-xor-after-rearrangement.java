class Solution {
    public String maximumXor(String s, String t) {
        int n = s.length();

        int[] tBinaryNumFreq = new int[2];
        for(char ch : t.toCharArray()) {
            int d = ch - '0';
            tBinaryNumFreq[d]++;
        }
        
        StringBuilder bStr = new StringBuilder();

        for(char ch : s.toCharArray()) {
            int d = ch - '0';

            if(d == 0) {
                if(tBinaryNumFreq[1] > 0) {
                    bStr.append('1');
                    tBinaryNumFreq[1]--;
                } else {
                    bStr.append('0');
                    tBinaryNumFreq[0]--;
                }
            } else {
                if(tBinaryNumFreq[0] > 0) {
                    bStr.append('1');
                    tBinaryNumFreq[0]--;
                } else {
                    bStr.append('0');
                    tBinaryNumFreq[1]--;
                }
            }
        }

        return bStr.toString();
    }
}