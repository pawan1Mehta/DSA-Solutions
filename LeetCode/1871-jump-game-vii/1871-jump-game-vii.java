class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        if(s.charAt(n - 1) == '1') {
            return false;
        }

        Queue<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n];

        visited[0] = true;
        bfs.add(0);

        int currMax = 0;

        while(!bfs.isEmpty()) {
            int currIdx = bfs.poll();

            if(currIdx == n-1) {
                return true;
            }

            for(int i = Math.max(currIdx + minJump, currMax); i <= Math.min(currIdx + maxJump, n - 1); i ++) {
                if(s.charAt(i) == '0' && !visited[i]) {
                    bfs.add(i);
                    visited[i] = true;
                }
            }

            currMax = Math.max(currMax, currIdx + maxJump + 1);
        }

        return false;
    }
}