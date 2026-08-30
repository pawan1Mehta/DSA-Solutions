class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        if(n == 1) {
            return true;
        }

        int[] oddSmallerLeft = oddSmallerLeft(nums1);
        int[] oddSmallerRight = oddSmallerRight(nums1);

        boolean isPossible = true;

        // is possible to make even
        for(int i = 0; i < n; i++) {
            if(nums1[i]%2 == 0) {
                continue;
            }

            if(i == 0) {
                if(oddSmallerRight[i + 1] > nums1[i]) {
                    isPossible = false;
                }
            } else if(i == n - 1) {
                if(oddSmallerLeft[i - 1] > nums1[i]) {
                    isPossible = false;
                }
            } else {
                if(oddSmallerLeft[i - 1] > nums1[i] && oddSmallerRight[i + 1] > nums1[i]) {
                    isPossible = false;
                }
            }

            if(!isPossible) {
                break;
            }
        }

        if(isPossible) {
            return true;
        }

        isPossible = true;
        // is possible to make odd
        for(int i = 0; i < n; i++) {
            if(nums1[i]%2 != 0) {
                continue;
            }

            if(i == 0) {
                if(oddSmallerRight[i + 1] > nums1[i]) {
                    isPossible = false;
                }
            } else if(i == n - 1) {
                if(oddSmallerLeft[i - 1] > nums1[i]) {
                    isPossible = false;
                }
            } else {
                if(oddSmallerLeft[i - 1] > nums1[i] && oddSmallerRight[i + 1] > nums1[i]) {
                    isPossible = false;
                }
            }

            if(!isPossible) {
                break;
            }
        }

        return isPossible;
    }

    private int[] oddSmallerLeft(int[] nums) {
        int n = nums.length;

        int[] oddSmallerLeft = new int[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if(nums[i]%2 != 0) {
                    oddSmallerLeft[i] = nums[i];
                } else {
                    oddSmallerLeft[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums[i]%2 != 0) {
                    oddSmallerLeft[i] = Math.min(nums[i], oddSmallerLeft[i - 1]);
                } else {
                    oddSmallerLeft[i] = oddSmallerLeft[i - 1];
                }
            }
        }

        return oddSmallerLeft;
    }

    private int[] oddSmallerRight(int[] nums) {
        int n = nums.length;

        int[] oddSmallerRight = new int[n];

        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                if(nums[i]%2 != 0) {
                    oddSmallerRight[i] = nums[i];
                } else {
                    oddSmallerRight[i] = Integer.MAX_VALUE;
                }
            } else {
                if(nums[i]%2 != 0) {
                    oddSmallerRight[i] = Math.min(nums[i], oddSmallerRight[i + 1]);
                } else {
                    oddSmallerRight[i] = oddSmallerRight[i + 1];
                }
            }
        }

        return oddSmallerRight;
    }
}
/**

    nums1 = [1,4,7]
        nums2 = [1,3,7] odd 

    nums1 = [2,3]
        nums2 = [-1, 3]
        
    nums1 = [4,6]
        nums2 = [4, 6]
        nusm2 = [4]
    
    if odd | even
        num - {1,7,11, 12,56....} >= 1

        if num is odd: want to make num even 
            find even targetNum which is smaller than num

        if num is even: want to make num odd
            find odd targetNum which is smaller than num


    odds = {.....}
    evens = {.....}

 */