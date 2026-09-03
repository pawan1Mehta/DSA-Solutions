class Solution {
    public int minCost(int n, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = constructuAdjList(n, edges);

        int[] dist = new int[n];
        Queue<int[]> bfs = new LinkedList<>();

        Arrays.fill(dist, Integer.MAX_VALUE);

        int srcNode = 0;

        bfs.add(new int[]{srcNode, 0});
        dist[srcNode] = 0;

        while(!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int u = curr[0];

            for(int[] adjNode : adjList.get(u)) {
                int v = adjNode[0];
                int wt = adjNode[1];

                if(dist[v] > (dist[u] + wt)) {
                    dist[v] = dist[u] + wt;
                    bfs.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }

    private ArrayList<ArrayList<int[]>> constructuAdjList(int n, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v, wt;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; wt = edge[2];
            adjList.get(u).add(new int[]{v, wt});
            adjList.get(v).add(new int[]{u, 2 * wt});
        }

        return adjList;
    }
}