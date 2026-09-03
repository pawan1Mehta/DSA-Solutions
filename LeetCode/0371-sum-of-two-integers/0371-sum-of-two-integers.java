class Solution {
    public int getSum(int a, int b) {
        int sum = 0;

        int carry = 0;
        for(int i = 0; i < 32; i++) {
            int mask = 1 << i;

            int aIthBit = a & mask;
            int bIthBit = b & mask;
            
            if(aIthBit == 0 && bIthBit == 0) {
                if(carry > 0) {
                    sum = sum | mask;
                    carry = 0;
                }
            } else if((aIthBit != 0 && bIthBit == 0) 
                || (aIthBit == 0 && bIthBit != 0)) {
                if(carry > 0) {
                    carry = 1;
                } else {
                    sum = sum | mask;
                } 
            } else if(aIthBit != 0 && bIthBit != 0) {
                if(carry > 0) {
                    sum = sum | mask;
                    carry = 1;
                } else {
                    carry = 1;
                }
            }
        }

        return sum;
    }
}