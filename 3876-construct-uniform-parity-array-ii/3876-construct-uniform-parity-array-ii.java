class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        boolean[] prefEven = new boolean[n];
        int[] prefEvenSmallNum = new int[n];

        boolean[] suffEven = new boolean[n];
        int[] suffSmallEvenNum = new int[n];

        boolean[] prefOdd = new boolean[n];
        int[] prefOddSmallNum = new int[n];

        boolean[] suffOdd = new boolean[n];
        int[] suffOddSmallNum = new int[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if(nums1[i]%2 == 0) {
                    prefEvenSmallNum[i] = nums1[i];
                    prefEven[i] = true;
                } else {
                    prefEvenSmallNum[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums1[i]%2 == 0) {
                    prefEven[i] = true;
                    prefEvenSmallNum[i] = Math.min(prefEvenSmallNum[i - 1], nums1[i]);
                } else {
                    prefEven[i] = prefEven[i - 1];
                    prefEvenSmallNum[i] = prefEvenSmallNum[i - 1];
                }
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                if(nums1[i]%2 == 0) {
                    suffSmallEvenNum[i] = nums1[i];
                    suffEven[i] = true;
                } else {
                    suffSmallEvenNum[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums1[i]%2 == 0) {
                    suffEven[i] = true;
                    suffSmallEvenNum[i] = Math.min(suffSmallEvenNum[i + 1], nums1[i]);
                } else {
                    suffEven[i] = suffEven[i + 1];
                    suffSmallEvenNum[i] = suffSmallEvenNum[i + 1];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if(nums1[i]%2 != 0) {
                    prefOdd[i] = true;
                    prefOddSmallNum[i] = nums1[i];
                } else {
                    prefOddSmallNum[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums1[i]%2 != 0) {
                    prefOdd[i] = true;
                    prefOddSmallNum[i] = Math.min(prefOddSmallNum[i - 1], nums1[i]);
                } else {
                    prefOdd[i] = prefOdd[i - 1];
                    prefOddSmallNum[i] = prefOddSmallNum[i - 1];
                }
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                if(nums1[i]%2 != 0) {
                    suffOddSmallNum[i] = nums1[i];
                    suffOdd[i] = true;
                } else {
                    suffOddSmallNum[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums1[i]%2 != 0) {
                    suffOdd[i] = true;
                    suffOddSmallNum[i] = Math.min(suffOddSmallNum[i + 1], nums1[i]);
                } else {
                    suffOdd[i] = suffOdd[i + 1];
                    suffOddSmallNum[i] = suffOddSmallNum[i + 1];
                }
            }
        }

        boolean allEven = true;
        boolean allOdd = true;

        for(int i = 0; i < n; i++) {
            if(nums1[i]%2 == 0) {
                int minNum = Integer.MAX_VALUE;

                boolean prevOdd = i > 0 ? prefOdd[i - 1]: false;
                if(i > 0) {
                    minNum = Math.min(minNum, prefOddSmallNum[i - 1]);
                }

                boolean nextOdd = i+1 < n ? suffOdd[i + 1]: false;
                if(i+1 < n) {
                    minNum = Math.min(minNum, suffOddSmallNum[i + 1]);
                }

                allOdd = allOdd & ((prevOdd | nextOdd) & nums1[i] > minNum);
            } else {int minNum = Integer.MAX_VALUE;

                boolean prevEven = i > 0 ? prefOdd[i - 1]: false;
                if(i > 0) {
                    minNum = Math.min(minNum, prefOddSmallNum[i - 1]);
                }

                boolean nextEven = i+1 < n ? suffOdd[i + 1]: false;
                if(i+1 < n) {
                    minNum = Math.min(minNum, suffOddSmallNum[i + 1]);
                }

                allEven = allEven & ((prevEven | nextEven) & nums1[i] > minNum);
            }
        }

        return allEven | allOdd;
    }
}