class Solution {
    
    private boolean isValid(int[] arr, long x) {
        for(int num : arr) {
            if(x >= 10000) {
                return true;
            }
            
            if(x > num) {
                long diff = Math.abs(x - num);
                x += diff;
            } else {
                long diff = Math.abs(num - x);
                x -= diff;
            }
            
            if(x < 0) {
                return false;
            }
        }    
        
        return x >= 0 ? true : false;
    }
    
    public int find(int[] arr) {
        int n = arr.length;
        
        long sum = 0;
        for(int num : arr) {
            sum += num;
        }
        
        long smallestNum = Integer.MAX_VALUE;
        long low = 1, high = sum;
        
        while(low <= high) {
            long mid = (low + high)/2;
            
            if(isValid(arr, mid)) {
                smallestNum = Math.min(smallestNum, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return (int) smallestNum;
    }
}
