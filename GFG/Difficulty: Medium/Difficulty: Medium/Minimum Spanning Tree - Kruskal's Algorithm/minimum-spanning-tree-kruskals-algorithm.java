// User function Template for Java

class DisjoinSet {
    int[] parent;
    int[] rank;
    
    DisjoinSet(int n) {
        parent = new int[n];
        rank = new int[n];
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    
    public void union(int node1, int node2) {
        int node1Pr = find(node1);
        int node2Pr = find(node2);
        
        if(node1Pr != node2Pr) {
            if(rank[node1Pr] > rank[node2Pr]) {
                parent[node2Pr] = node1Pr;
            } else if(rank[node1Pr] < rank[node1Pr]) {
                parent[node1Pr] = node2Pr;
            } else {
                parent[node2Pr] = node1Pr;
                rank[node1Pr]++;
            }
        }    
    }
}

class Solution {
    static int kruskalsMST(int V, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        
        DisjoinSet ds = new DisjoinSet(V);
        
        int cost = 0, count = 0;
        
        int u, v, wt;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; wt = edge[2];
            
            if(ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                
                cost += wt;
                count++;
                
                if(count == V-1) {
                    break;
                }
            }
        }
        
        return cost;
    }
}
