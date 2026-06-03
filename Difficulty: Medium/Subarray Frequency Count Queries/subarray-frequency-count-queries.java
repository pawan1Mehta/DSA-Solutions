class Solution {
    
    public ArrayList<Integer> freqInRange(int[] arr, int[][] queries) {
        int n = arr.length;
        
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            mp.putIfAbsent(arr[i], new ArrayList<>());
            mp.get(arr[i]).add(i);
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        int l, r, x;
        for(int[] query : queries) {
            l = query[0];
            r = query[1];
            x = query[2];
            
            if(mp.containsKey(x)) {
                ArrayList<Integer> list = mp.get(x);
                
                int leftIndex = Collections.binarySearch(list, l);
                int rightIndex = Collections.binarySearch(list, r + 1);
                
                if(leftIndex < 0) {
                    leftIndex = -(leftIndex + 1);
                }
                if(rightIndex < 0) {
                    rightIndex = -(rightIndex + 1);
                }
                
                res.add(rightIndex - leftIndex);
            } else {
                res.add(0);
            }
        }
        
        return res;
    }
}