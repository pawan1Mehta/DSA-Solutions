class Solution {

    Map<String, Boolean> memo;

    private boolean buildPayramid(int index, StringBuilder newStr, String bottom, Map<String, List<Character>> allowedStr, int n) {
        if(n == 1) {
            return true;
        }

        if(index == n-1) {
            if(memo.containsKey(newStr.toString())) {
                return memo.get(newStr.toString());
            }

            memo.put(newStr.toString(), buildPayramid(0, new StringBuilder(), newStr.toString(), allowedStr, n - 1));

            return memo.get(newStr.toString());
        }

        String tempStr = bottom.substring(index, index + 2);

        if(!allowedStr.containsKey(tempStr)) {
            return false;
        }

        for(char ch : allowedStr.get(tempStr)) {
            StringBuilder nextStr = new StringBuilder(newStr).append(ch);
            if(buildPayramid(index + 1, nextStr, bottom, allowedStr, n)) {
                return true;
            }
        }

        return false;
    }


    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<Character>> mp = new HashMap<>();

        for(String str : allowed) {
            String tempStr = str.substring(0, 2);
            mp.putIfAbsent(tempStr, new ArrayList<>());
            mp.get(tempStr).add(str.charAt(2));
        }

        memo = new HashMap<>();

        return buildPayramid(0, new StringBuilder(), bottom, mp, bottom.length());
    }
}