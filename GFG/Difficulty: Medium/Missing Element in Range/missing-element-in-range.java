class Solution {
    public ArrayList<Integer> missingRange(int[] arr, int low, int high) {
        int n = arr.length;
        
        Arrays.sort(arr);
        
        ArrayList<Integer> resList = new ArrayList<>();
        
        int i = 0;
        while(i < n && arr[i] < low) {
            i++;
        }
        
        if(i == n) {
            addNum(low, high, resList);
            return resList;
        } else if(arr[i] > low) {
            addNum(low, Math.min(arr[i] - 1, high), resList);
        }
        
        int nextNum = arr[i] + 1;
        
        i++;
        while(i < n && nextNum <= high) {
            if(arr[i] > nextNum) {
                addNum(nextNum, Math.min(arr[i] - 1, high), resList);
            }
            
            nextNum = arr[i] + 1;
            i++;
        }
        
        if(nextNum <= high) {
            addNum(nextNum, high, resList);    
        }
        
        return resList;
    }
    
    private void addNum(int start, int end, ArrayList<Integer> list) {
        for(int i = start; i <= end; i++) {
            list.add(i);
        }
    }
}