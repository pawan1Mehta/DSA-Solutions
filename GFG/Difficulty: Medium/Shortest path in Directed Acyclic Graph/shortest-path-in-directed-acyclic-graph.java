// User function Template for Java
class Solution {
    
    private void dfs(int currNode, List<List<int[]>> adjList, boolean[] visited, Stack<Integer> st) {
        visited[currNode] = true;
        
        for(int[] adjNode : adjList.get(currNode)) {
            if(!visited[adjNode[0]]) {
                dfs(adjNode[0], adjList, visited, st);
            }
        }
        
        st.add(currNode);
    }
    
    private int[] topologicalSort(int V, List<List<int[]>> adjList) {
        int[] resList = new int[V];
        
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                dfs(i, adjList, visited, st);
            }
        }
        
        for(int i = 0; i < V; i++) {
            resList[i] = st.pop();
        }
        
        return resList;
    }
    

    public int[] shortestPath(int V, int E, int[][] edges) {
        List<List<int[]>> adjList = constructAdjList(V, edges);
        int[] toposort = topologicalSort(V, adjList);
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[0] = 0;
        
        int u, v, dt;
        for(int i = 0; i < V; i++) {
            u = toposort[i];
            if(dist[u] != Integer.MAX_VALUE) {
                for(int[] adjNode : adjList.get(u)) {
                    v = adjNode[0]; dt = adjNode[1];
                    
                    if(dist[v] > (dist[u] + dt)) {
                        dist[v] = (dist[u] + dt);
                    }
                }
            }
        }
        
        for(int i = 0; i < V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        
        return dist;
    }
    
    private List<List<int[]>> constructAdjList(int V, int[][] edges) {
        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int u, v, dt;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; dt = edge[2];
            
            adjList.get(u).add(new int[]{v, dt});
        }
        
        return adjList;
    }
}