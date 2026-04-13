class Solution {

    private int findNearestNum(ArrayList<Integer> nums, int num) {
        int n = nums.size();

        int l = 0, r = n - 1;
        int targetIdx = 0;

        while(l <= r) {
            int mid = (l + r)/2;

            if(nums.get(mid) >= num) {
                targetIdx = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return targetIdx;
     }
    
    public int minOperations(int[] nums) {
        int MAX = 0;

        for(int num : nums) {
            MAX = Math.max(MAX, num);
        }
        MAX += 100;
        
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
    
        for(int num = 2; num < MAX; num++) {
            for(int i = 2 * num; i < MAX; i += num) {
                isPrime[i] = false;
            }
        }

        ArrayList<Integer> primeNums = new ArrayList<>();
        ArrayList<Integer> nonPrimeNums = new ArrayList<>();

        for(int i = 1; i < MAX; i++) {
            if(isPrime[i]) {
                primeNums.add(i);
            } else {
                nonPrimeNums.add(i);
            }
        }

        int operations = 0;
        for(int i = 0; i < nums.length; i++) {
            int targetIndx = nums[i];
            int minOpe = Integer.MAX_VALUE;
            
            if(i%2 == 0) {
                targetIndx = findNearestNum(primeNums, nums[i]);
                minOpe = Math.min(minOpe, Math.abs(nums[i] - primeNums.get(targetIndx)));
            } else {
                targetIndx = findNearestNum(nonPrimeNums, nums[i]);
                minOpe = Math.min(minOpe, Math.abs(nums[i] - nonPrimeNums.get(targetIndx)));
            }

            operations += minOpe;
        }

        return operations;
    }
}