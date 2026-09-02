class Solution {
    public long factorial(int n) {
        if(n == 0) {
            return 1;
        }
        
        int sum = n;
        
        for(int i = n - 1; i >= 1; i--) {
            sum = sum * i;
        }
        
        return sum;
    }

    public int[] getDigits(long n) {
        StringBuilder num = new StringBuilder();
        
        while(n > 0) {
            long d = n % 10;
            num.append(d);
            n = n/10;
        }
        num.reverse();

        int[] digits = new int[num.length()];
        for(int i = 0; i < digits.length; i++) {
            int d = num.charAt(i) - '0';
            digits[i] = d;
        }

        return digits;
    }
    
    public boolean isDigitorialPermutation(int n) {
        int[] digits = getDigits(n);
        
        long factorials = 0;
        for(int digit : digits) {
            factorials += factorial(digit);
        }

        if(factorials == n) {
            return true;
        }
        
        int[] fDigits = getDigits(factorials);

        Map<Integer, Integer> digitMap = new HashMap<>();
        Map<Integer, Integer> fDigitMap = new HashMap<>();
        for(int digit : digits) {
            digitMap.put(digit, digitMap.getOrDefault(digit, 0) + 1);
        }
        for(int digit : fDigits) {
            fDigitMap.put(digit, fDigitMap.getOrDefault(digit, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> d : digitMap.entrySet()) {
            int key = d.getKey();
            int val = d.getValue();

            if(!fDigitMap.containsKey(key)) {
                return false;
            }
            
            fDigitMap.put(key, fDigitMap.get(key) - val);
        }
 
        for(int val : fDigitMap.values()) {
            if(val != 0) {
                return false;
            }
        }

        return true;
    }
}