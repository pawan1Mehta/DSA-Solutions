class Solution {
    
    static int discTime = 0;
    
    static void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adjList, int[] disc, int[] low, boolean[] visited, boolean[] isArtPoints) {
        disc[node] = low[node] = discTime++;
        visited[node] = true;
        int child = 0;
        
        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                child++;
                
                dfs(adjNode, node, adjList, disc, low, visited, isArtPoints);
                
                low[node] = Math.min(low[node], low[adjNode]);
                
                if(parent != -1 && low[adjNode] >= disc[node]) {
                    isArtPoints[node] = true;
                }
            } else if(adjNode != parent) {
                low[node] = Math.min(low[node], disc[adjNode]);
            }
        }
        
        if(parent == -1 && child > 1) {
            isArtPoints[node] = true;
        }
    }
    
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
        
        boolean[] isArtPoints = new boolean[V];
        
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(visited, false);
        
        discTime = 0;
        
        for(int node = 0; node < V; node++) {
            if(!visited[node]) {
                dfs(node, -1, adjList, disc, low, visited, isArtPoints);
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(int node = 0; node < V; node++) {
            if(isArtPoints[node]) {
                res.add(node);
            }
        }
        
        if(res.size() == 0) {
            res.add(-1); 
        }
        
        return res;
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