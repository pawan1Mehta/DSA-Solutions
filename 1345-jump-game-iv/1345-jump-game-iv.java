class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> idx = new HashMap<>();

        for(int i = 0; i < n; i++) {
            idx.putIfAbsent(arr[i], new ArrayList<>());
            idx.get(arr[i]).add(i);
        }

        Queue<int[]> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        bfs.add(new int[]{0, 0});
        visited[0] = true;

        while(!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int node = curr[0];
            int dist = curr[1];

            if(node == n - 1) {
                return dist;
            }

            if(node - 1 >=0 && !visited[node - 1]) {
                visited[node - 1] = true;
                bfs.add(new int[]{node - 1, dist + 1});
            }

            if(node + 1 < n && !visited[node + 1]) {
                visited[node + 1] = true;
                bfs.add(new int[]{node + 1, dist + 1});
            }

            for(int j : idx.get(arr[node])) {
                if(!visited[j]) {
                    visited[j] = true;
                    bfs.add(new int[]{j, dist + 1});
                }
            }

            idx.get(arr[node]).clear();
        }

        return -1;
    }
}