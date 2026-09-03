class Solution {
    public int maxSumDivThree(int[] nums) {
        int maxSum = 0;

        ArrayList<Integer> r1 = new ArrayList<>();
        ArrayList<Integer> r2 = new ArrayList<>();

        int sum = 0;
        
        for(int num : nums) {
            sum += num;
            if(num % 3 == 1) {
                r1.add(num);
            } else if(num % 3 == 2) {
                r2.add(num);
            }
        }

        if(sum%3 == 0) {
            return sum;
        }  

        Collections.sort(r1);
        Collections.sort(r2);
        
        int rem = sum % 3;

        if(rem == 1) {
            int opt1 = r1.size() >= 1 ? sum - r1.get(0) : 0;
            int opt2 = r2.size() >= 2 ? sum - r2.get(0) - r2.get(1) : 0;
            maxSum = Math.max(opt1, opt2);
        } else {
            int opt1 = r2.size() >= 1 ? sum - r2.get(0) : 0;
            int opt2 = r1.size() >= 2 ? sum - r1.get(0) - r1.get(1) : 0;
            maxSum = Math.max(opt1, opt2);
        }

        return maxSum;
    }
}