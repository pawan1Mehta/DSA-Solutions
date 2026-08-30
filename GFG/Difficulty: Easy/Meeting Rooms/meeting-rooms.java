class Solution {
    
    static boolean isOverlapping(int[] time1, int[] time2) {
        if(time1[1] > time2[0]) {
            return true;
        }
        return false;
    }
    
    static boolean canAttend(int[][] arr) {
        int n = arr.length;
        
        Arrays.sort(arr, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });
        
        for(int i = 1; i < n; i++) {
            if(isOverlapping(arr[i-1], arr[i])) {
                return false;
            }
        }
        
        return true;
    }
}