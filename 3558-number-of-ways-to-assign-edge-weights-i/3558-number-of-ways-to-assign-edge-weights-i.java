class Solution {
    private static final int MOD = 1_000_000_007;

    private int dfs(int node, int parent, List<List<Integer>> adjList) {
        int maxDepth = 0;

        for(int adjNode : adjList.get(node)) {
            if(adjNode == parent) continue;
            maxDepth = Math.max(maxDepth, 1 + dfs(adjNode, node, adjList));
        }
        
        return maxDepth;
    }

    private int power(long x, long y) {
        long res = 1;

        while(y > 0) {
            int bit = (y & 1) != 0 ? 1 : 0;
            
            if(bit == 1) {
                res = (res * x) % MOD;
            }

            x = (x * x) % MOD;

            y = y >> 1;
        }

        return (int) res;
    }

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<List<Integer>> adjList = constructAdjList(n, edges);

        int maxDepth = dfs(1, -1, adjList);

        return power((long) 2, (long) maxDepth - 1);
    }

    private List<List<Integer>> constructAdjList(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        return adjList;
    }
}