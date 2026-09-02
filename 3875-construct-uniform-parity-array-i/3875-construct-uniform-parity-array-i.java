class Solution {

    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        boolean[] prefEven = new boolean[n];
        boolean[] suffEven = new boolean[n];
        boolean[] prefOdd = new boolean[n];
        boolean[] suffOdd = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                prefEven[i] = (nums1[i]%2 == 0 ? true : false);
            } else {
                prefEven[i] = prefEven[i - 1] | (nums1[i]%2 == 0 ? true : false);
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                suffEven[i] = nums1[i]%2 == 0 ? true : false;
            } else {
                suffEven[i] = suffEven[i + 1] | (nums1[i]%2 == 0 ? true : false);
            }
        }

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                prefOdd[i] = (nums1[i]%2 == 0 ? false : true);
            } else {
                prefOdd[i] = prefOdd[i - 1] | (nums1[i]%2 == 0 ? false : true);
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                suffOdd[i] = (nums1[i]%2 == 0 ? false : true);
            } else {
                suffOdd[i] = suffOdd[i + 1] | (nums1[i]%2 == 0 ? false : true);
            }
        }

        boolean allEven = true;
        boolean allOdd = true;

        for(int i = 0; i < n; i++) {
            if(nums1[i]%2 == 0) {
                boolean prevOdd = i > 0 ? prefOdd[i - 1]: false;
                boolean nextOdd = i+1 < n ? suffOdd[i + 1]: false;
                allOdd = allOdd & (prevOdd | nextOdd);
            } else {
                boolean prevEven = i > 0 ? prefEven[i - 1]: false;
                boolean nextEven = i+1 < n ? suffEven[i + 1]: false;
                allEven = allEven & (prevEven | nextEven);
            }
        }

        return allEven | allOdd;
    }
}