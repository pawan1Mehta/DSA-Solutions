class Solution {
    private static int discTime = 0;
    
    private void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int[] disc, int[] low, ArrayList<int[]> bridges){
        visited[node] = true;
        disc[node] = low[node] = discTime++;
        
        for(int adjNode : adjList.get(node)) {
            if(adjNode == parent) {
                continue;
            }
            
            if(!visited[adjNode]) {
                dfs(adjNode, node, adjList, visited, disc, low, bridges);
                
                low[node] = Math.min(low[node], low[adjNode]);
                
                if(low[adjNode] > disc[node]) {
                    bridges.add(new int[]{node, adjNode});
                }
            } else {
                low[node] = Math.min(low[node], disc[adjNode]);
            }
        }
    }
    
    public boolean isBridge(int V, int[][] edges, int c, int d) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
        
        ArrayList<int[]> bridges = new ArrayList<>();
        
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        
        discTime = 0;
        
        for(int node = 0; node < V; node++) {
            if(!visited[node]) {
                dfs(node, -1, adjList, visited, disc, low, bridges);
            }
        }
        
        int u, v;
        for(int[] edge : bridges) {
            u = edge[0]; v = edge[1];
            
            if(u == c && v == d || u == d && v == c) {
                return true;
            }
        }
        
        return false;
    }
    
    static ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
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