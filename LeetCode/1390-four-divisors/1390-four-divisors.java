class Solution {
    private List<Integer> divisors(int num) {
        List<Integer> divs = new ArrayList<>();

        for(int i = 1; i <= Math.sqrt(num); i++) {
            if(num%i == 0) {
                int a = i;
                int b = num/a;

                divs.add(a);
                if(a != b) {
                    divs.add(b);
                }
            }
        }    

        return divs;
    }

    private int getSum(List<Integer> nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        return sum;
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;

        for(int num : nums) {
            List<Integer> divs = divisors(num);
            if(divs.size() == 4) {
                sum += getSum(divs);
            }
        }

        return sum;
    }
}