class Solution {
    public boolean bfs(int src, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        Queue<int[]> bfs = new LinkedList<>();
        
        bfs.add(new int[]{src, 0});
        color[src] = 0;
        
        int node, col;
        while(!bfs.isEmpty()) {
            int[] currNode = bfs.poll();
            
            node = currNode[0]; col = currNode[1];
            
            for(int adjNode : adjList.get(node)) {
                if(color[adjNode] == col) {
                    return false;
                } else {
                    if(color[adjNode] == -1) {
                        color[adjNode] =  col == 0 ? 1 : 0;
                        bfs.add(new int[]{adjNode, color[adjNode]});
                    }
                }
            }
        }
        
        return true;
    }
    
    public boolean isBipartite(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(V, edges);
        
        int[] color = new int[V];
        Arrays.fill(color, -1);
        
        for(int i = 0; i < V; i++) {
            if(color[i] == -1) {
                if(!bfs(i, adjList, color)) {
                    return false;
                }
            }
        }
        
        
        return true;
    }
    
    public ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
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