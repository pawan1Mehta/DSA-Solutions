class Solution {
    boolean[] isPrime;

    private void sieveOfEratoshenesAlgo(int n) {
        isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        for(int i = 2; i <= n; i++) {
            if(isPrime[i]) {
                for(int j = 2 * i; j <= n; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        isPrime[0] = isPrime[1] = false;
    }

    public int countPrimeSetBits(int left, int right) {
        sieveOfEratoshenesAlgo(right);

        int count = 0;
        
        for(int num = left; num <= right; num++) {
            System.out.println("num: " + num + " bit: " + Integer.bitCount(num) + " isPrime: " + isPrime[Integer.bitCount(num)]);
            if(isPrime[Integer.bitCount(num)]) {
                count++;
            }
        }

        return count;
    }
}