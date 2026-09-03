class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        ArrayList<Integer> res = new ArrayList<>();

        int carry = 1;

        for(int i = n - 1; i >= 0; i--) {
            int num = carry + digits[i];
            res.add(num%10);
            carry = num / 10;
        }

        if(carry > 0) {
            res.add(carry);
        }

        int[] resArr = new int[res.size()];
        for(int i = res.size() -1, j = 0; i >= 0; i--, j++) {
            resArr[j] = res.get(i);
        }

        return resArr;
    }
}