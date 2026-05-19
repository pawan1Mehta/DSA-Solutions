class Solution {
    public int minSteps(int[] arr, int start, int end) {
        if(start == end) {
            return 0;
        }
        
        int mod = 1000;
        
        Queue<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[mod + 1];
        
        for(int num : arr) {
            int newStart = (num * start) % mod;
            bfs.add(newStart);
            visited[newStart] = true;
        }
        
        int count = 1;
        
        while(!bfs.isEmpty()) {
            int n = bfs.size();
            
            while(n-- > 0) {
                int nextStart = bfs.poll();
                
                if(nextStart == end) {
                    return count;
                }
                
                for(int num : arr) {
                    int newStart = (num * nextStart) % mod;
                    if(!visited[newStart]) {
                        bfs.add(newStart);
                        visited[newStart] = true;
                    }
                }
            }
            
            count++;
        }
        
        return -1;
    }
}