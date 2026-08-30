class Solution {
    public int maxAmount(int[] arr, int k) {
        int MOD = 1_000_000_007;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int num : arr) {
            maxHeap.add(num);
        }
        
        int maxAmount = 0;
        
        while(k-- > 0) {
            int num = maxHeap.poll();
            if(num < 0) {
                break;
            }
            maxAmount = (maxAmount + num) % MOD;
            maxHeap.add(num - 1);
        }
        
        return maxAmount;
    }
}