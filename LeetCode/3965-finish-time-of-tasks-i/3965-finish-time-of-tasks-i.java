class Solution {

    private long dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] baseTime) {
        if(adjList.get(node).size() == 0) {
            return baseTime[node];
        }    

        long earliest = Long.MAX_VALUE;
        long latest = Long.MIN_VALUE;

        for(int adjNode : adjList.get(node)) {
            long time = dfs(adjNode, adjList, baseTime);
            earliest = Math.min(earliest, time);
            latest = Math.max(latest, time);
        }

        long ownDuration = (latest - earliest) + baseTime[node];

        return latest + ownDuration;
    }
    
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(n, edges);

        return dfs(0, adjList, baseTime);
    }

    private ArrayList<ArrayList<Integer>> constructAdjList(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adjList.get(u).add(v);
        }

        return adjList;
    }
}