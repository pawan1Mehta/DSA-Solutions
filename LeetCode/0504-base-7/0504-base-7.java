class Solution {
    public String convertToBase7(int num) {
        if(num == 0) {
            return String.valueOf(0);
        }
        
        boolean isNegative = false;
        
        if(num < 0) {
            isNegative = true;
        }
        
        num = Math.abs(num);
        
        int base7 = 7;
        
        StringBuilder base7Num = new StringBuilder();
        
        while(num > 0) {
            int remainder = num % base7;
            base7Num.append(remainder);
            num = num/base7;
        }
        
        if(isNegative) {
            base7Num.append("-");
        }
        
        return base7Num.reverse().toString();
    }
}