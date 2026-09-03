class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1_000_000_007;

        Map<Integer, Integer> rightSideNums = new HashMap<>();

        for(int num : nums) {
            rightSideNums.put(num, rightSideNums.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> leftSideNums = new HashMap<>();
        long count = 0;

        for(int num : nums) {
            rightSideNums.put(num, rightSideNums.get(num) - 1);
            if(rightSideNums.get(num) == 0) {
                rightSideNums.remove(num);
            }

            int target = num * 2;

            if (leftSideNums.containsKey(target) && rightSideNums.containsKey(target)) {
                long leftCount = leftSideNums.get(target);
                long rightCount = rightSideNums.get(target);
                long mul = leftCount * rightCount;
                count = (count + mul % MOD) % MOD;
            }

            leftSideNums.put(num, leftSideNums.getOrDefault(num, 0) + 1);
        }

        return (int) count;
    }
}