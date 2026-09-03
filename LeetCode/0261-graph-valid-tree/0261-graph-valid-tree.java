class Solution {
    private boolean isCycle(int node, int parent, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[node] = true;

        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                if(isCycle(adjNode, node, adjList, visited)) {
                    return true;
                }
            } else if(adjNode != parent) {
                return true;
            }
        }

        return false;
    }
    public boolean validTree(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(n, edges);

        boolean[] visited = new boolean[n];
        if(isCycle(0, -1, adjList, visited)) {
            return false;
        }

        for(int node = 0; node < n; node++) {
            if(!visited[node]) {
                return false;
            }
        }

        return true;
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
            adjList.get(v).add(u);
        }

        return adjList;
    }
}