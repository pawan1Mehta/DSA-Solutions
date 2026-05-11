class Solution {
    public int[] separateDigits(int[] nums) {
        int n = nums.length;

        ArrayList<Integer> res = new ArrayList<>();

        for(int num : nums) {
            ArrayList<Integer> digits = new ArrayList<>();
            while(num > 0) {
                int digit = num % 10;
                num = num/10;
                digits.add(digit);
            }

            Collections.reverse(digits);

            for(int digit : digits) {
                res.add(digit);
            }
        }

        int[] resArr = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }

        return resArr;
    }
}