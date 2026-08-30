class Solution {

    private void generateValidStringsUtil(StringBuilder str, int n, int k, List<String> res) {
        if(n == 0) {
            res.add(str.toString());
            return;
        }

        str.append('0');
        generateValidStringsUtil(str, n - 1, k, res);
        str.deleteCharAt(str.length() - 1);

        if((k - str.length()) >= 0 && ((str.length() == 0) || str.charAt(str.length() - 1) == '0')) {
            k = k - str.length();
            str.append('1');
            generateValidStringsUtil(str, n - 1, k, res);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public List<String> generateValidStrings(int n, int k) {
        List<String> res = new ArrayList<>();
        StringBuilder currBinaryStr = new StringBuilder();
        generateValidStringsUtil(currBinaryStr, n, k, res);
        return res;
    }
}