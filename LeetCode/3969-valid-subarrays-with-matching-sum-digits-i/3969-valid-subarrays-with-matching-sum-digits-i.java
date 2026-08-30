class Solution {

    private int[] findFirstAndLastDigit(long num) {
        int first = (int) (num%10);
        int last = (int) (num%10);

        num /= 10;

        while(num > 0) {
            int d = (int) (num % 10);
            last = d;
            num /= 10;
        }

        return new int[]{first, last};
    }

    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;

        int count = 0;

        for(int i = 0; i < n; i++) {
            long sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];

                int[] digits = findFirstAndLastDigit(sum);
                if(digits[0] == digits[1] && digits[0] == x) {
                    count++;
                }
            }
        }

        return count;
    }
}