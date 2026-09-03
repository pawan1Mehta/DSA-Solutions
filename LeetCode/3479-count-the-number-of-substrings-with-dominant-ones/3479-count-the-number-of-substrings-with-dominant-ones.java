class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();

        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - '0';
        }

        for(int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        int count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int ones = nums[j] - (i > 0 ? nums[i - 1] : 0);
                int zeros = (j - i + 1) - ones;
               
                if((zeros * zeros) > ones) {
                    // skip the invalid indicies
                    int wastedIndices = (zeros * zeros) - ones;
                    j += wastedIndices - 1;
                } else if ((zeros * zeros) == ones) {
                    count++;
                } else {
                    count++;

                    /*
                        how many indices we can skip until the current substr is valid
                        
                        11111011
                             |
                        ones = 5
                        zeros = 1
                        1*1 = 1
                        5 > 1

                        ones > zeros * zeros 

                        (zeros)^2 <= ones

                        zero <= sqrt(ones)   

                        let's say we shifted by and the next shift gave us '0'

                        zeros + 1  <= sqrt(ones)
                        ....
                        ....
                        ...
                        zeros + k == sqrt(ones)

                        k = sqrt(ones) - zeros

                    */

                    int k = (int) Math.sqrt(ones) - zeros;
                    int next = j + k;

                    if(next >= n) {
                        count += (n - j -1);
                        break;
                    } else {
                        count += k;
                    }

                    j = next;
                }
            }
        }

        return count;
    }
}