class Solution {

    private int waviness(int num) {
        if(num <= 99) {
            return 0;
        }

        int wavie = 0;

        int prev = num % 10;
        num = num/10;
        int curr = num % 10;
        num = num/10;
        
        while(num > 0) {
            int next = num%10;

            if((prev < curr && curr > next) || (prev > curr && curr < next)) {
                wavie++;
            }

            prev = curr;
            curr = next;
            
            num = num/10;
        }
        
        return wavie;
    }

    public int totalWaviness(int num1, int num2) {
        int count = 0;

        for(int num = num1; num <= num2; num++) {
            count += waviness(num);
        }

        return count;
    }
}