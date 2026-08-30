// User function Template for Java

class Solution {
    private static int discTime = 0;
    
    static boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adjList, int[] disc, int[] low) {
        disc[node] = low[node] = discTime++;
        int child = 0;
        
        for(int adjNode : adjList.get(node)) {
            if(disc[adjNode] == -1) {
                child++;
                
                if(dfs(adjNode, node, adjList, disc, low)) {
                    return true;
                }
                
                low[node] = Math.min(low[node], low[adjNode]);
                
                if(parent != -1 && low[adjNode] >= disc[node]) {
                    return true;
                }
            } else if(adjNode != parent) {
                low[node] = Math.min(low[node], disc[adjNode]);
            }
        }
        
        if(parent == -1 && child > 1) {
            return true;
        }
        
        return false;
    }
    
    static int biGraph(int[] arr, int n, int e) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int i = 0; i < arr.length; i += 2) {
            int u = arr[i];
            int v = arr[i + 1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        int[] disc = new int[n];
        int[] low = new int[n];
        
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        
        if(dfs(0, -1, adjList, disc, low)) {
            return 0;
        }
        
        for(int node = 0; node < n; node++) {
            if(disc[node] == -1) {
                return 0;
            }
        }
        
        return 1;
    }
};