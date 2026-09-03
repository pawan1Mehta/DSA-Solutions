class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;

        int n = arr.length;

        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();

        int[] leftBoundry = new int[n];
        int[] rightBoundry = new int[n];

        for(int i = 0; i < n; i++) {
            while(!left.isEmpty() && arr[left.peek()] > arr[i]) {
                left.pop();
            }
            leftBoundry[i] = left.isEmpty() ? i + 1 : (i - left.peek());
            left.push(i);
        }

        for(int i = n - 1; i >= 0; i--) {
            while(!right.isEmpty() && arr[right.peek()] >= arr[i]) {
                right.pop();
            }
            rightBoundry[i] = right.isEmpty() ? (n - i) : (right.peek() - i);
            right.push(i);
        }

        long sum = 0;
        for(int i = 0; i < n; i++) {
            long numSub = ((long) arr[i] *  (((long)leftBoundry[i] * rightBoundry[i]) % MOD)) % MOD;
            sum = (sum + numSub) % MOD;
        }

        return (int)sum;
    }
}