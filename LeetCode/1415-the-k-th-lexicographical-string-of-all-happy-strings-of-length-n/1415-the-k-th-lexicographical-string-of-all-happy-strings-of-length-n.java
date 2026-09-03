class Solution {
    private char[] hChars = new char[]{'a', 'b', 'c'};
    private List<String> happyStrings;

    private void buildHappyString(StringBuilder str, int n) {
        if(n == 0) {
            happyStrings.add(str.toString());
            return;
        }

        int sN = str.length();

        for(char ch : hChars) {
            if(sN == 0 || ch != str.charAt(sN - 1)) {
                str.append(ch);
                buildHappyString(str, n - 1);
                str.deleteCharAt(sN);
            }
        }
    }

    public String getHappyString(int n, int k) {
        happyStrings = new ArrayList<>();

        buildHappyString(new StringBuilder(), n);

        return happyStrings.size() < k ? "" : happyStrings.get(k - 1);
    }
}
/**

    Generate all the valid strings

        chars = {a, b, c}


    buildHappyString(str, n)
        if n == 0
          list.add(str)
        return

       for ch : chars 
         if ch != str[n - 1]
            add ch -> buildHappyString(str + ch, n - 1)

 */