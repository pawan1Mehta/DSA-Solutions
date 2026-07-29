class Solution {

    private int binarySearch(ArrayList<Integer> list, int num, int target) {
        int n = list.size();

        int low = 0, high = n - 1;
        int index = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if((list.get(mid) + num) < target) {
                index = Math.max(index, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return index;
    }

    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;

        int count = 0;

        for(int i = 1; i < n - 1; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                list.add(nums[j] + nums[i]);
            }

            Collections.sort(list);

            for(int j = i + 1; j < n; j++) {
                int index = binarySearch(list, nums[j], target);
                if(index != -1) {
                    count += index + 1;
                }
            }
        }

        return count;
    }
}