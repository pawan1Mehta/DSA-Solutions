class Solution {
    private int findIndex(ArrayList<Integer> nums, int num) {
        int n = nums.size();
        
        int index = 0;
        int left = 0, right = n - 1;

        while(left <= right) {
            int mid = (left + right);
            if(nums.get(mid) < num) {
                index = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }

    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lis = new ArrayList<>();

        for(int num : nums) {
            if(lis.size() == 0 || lis.get(lis.size() - 1) < num) {
                lis.add(num);
            } else {
                int index = findIndex(lis, num);
                lis.set(index, num);
            }
        }

        return lis.size();
    }
}