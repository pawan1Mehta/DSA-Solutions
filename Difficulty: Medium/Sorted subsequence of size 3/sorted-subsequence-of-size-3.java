class Solution {
    public ArrayList<Integer> find3Numbers(int[] arr) {
        int n = arr.length;
        
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                leftMin[i] = arr[i];
            } else {
                leftMin[i] = Math.min(leftMin[i - 1], arr[i]);
            }
        }
        for(int i = n-1; i >= 0; i--) {
            if(i == n-1) {
                rightMax[i] = arr[i];
            } else {
                rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        for(int i = 1; i < n - 1; i++) {
            if(leftMin[i - 1] < arr[i] && rightMax[i + 1] > arr[i]) {
                res.add(leftMin[i - 1]);
                res.add(arr[i]);
                res.add(rightMax[i + 1]);
                break;
            }
        }
        
        return res;
    }
}
/*
    104 753 852 120 676 984
    104 104 104 104 104 104
            984 984 984 984

*/