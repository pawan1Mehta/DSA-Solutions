class Solution {

    private int maxTwoPowerNum(int num) {
        for(int pow = 1; pow < 26; pow++) {
            if((int) Math.pow(2, pow) > num) {
                return (int) Math.pow(2, pow - 1);
            }
        }
        return (int) Math.pow(2, 25);
    }

    public String[] largestString(int[] nums) {
        Map<Integer, Character> numCharMp = new HashMap<>();
        char ch = 'a';
        for(int num = 0; num < 26; num++, ch++) {
            numCharMp.put((int) Math.pow(2, num), ch);
        }

        int n = nums.length;

        String[] res = new String[n];
        for(int i = 0; i < n; i++) {
            int num = nums[i];
            StringBuilder str = new StringBuilder();
            while(num > 0) {
                int _maxTwoPowerNum = maxTwoPowerNum(num);
                str.append(numCharMp.get(_maxTwoPowerNum));
                num = num - _maxTwoPowerNum;
            }
            res[i] = str.toString();
        }

        return res;
    }
}