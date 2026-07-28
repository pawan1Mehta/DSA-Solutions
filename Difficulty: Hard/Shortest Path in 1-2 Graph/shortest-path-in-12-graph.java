class Solution {
    
    private int bfs(ArrayList<ArrayList<Integer>> adjList, int src, int dest) {
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.add(new int[]{src, 0});
        visited.add(src);
        
        while(!queue.isEmpty()) {
            int n = queue.size();
            while(n-- > 0) {
                int[] curr = queue.poll();
                
                int node = curr[0];
                int count = curr[1];
                
                if(node == dest) {
                    return count;
                }
                
                for(int adjNode : adjList.get(node)) {
                    if(!visited.contains(adjNode)) {
                        queue.add(new int[]{adjNode, count + 1});
                        visited.add(adjNode);
                    }
                }
            }
        }
        
        return -1;
    }
    
    public int shortestPath(int V, int src, int dest, int[][] edges) {
        int extraNode = V;
        
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i < (V + edges.length); i++) {
            adjList.add(new ArrayList<>());    
        }
        
        int u, v, wt;
        for(int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            wt = edge[2];
            
            if(wt == 1) {
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            } else {
                
                adjList.get(u).add(extraNode);
                adjList.get(extraNode).add(u);
                
                adjList.get(extraNode).add(v);
                adjList.get(v).add(extraNode);
                
                extraNode++;
            }
        }
        
        return bfs(adjList, src, dest);
    }
}