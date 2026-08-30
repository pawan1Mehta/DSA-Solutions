
class DisjoinSet {
    int[] parent;
    int[] rank;
    
    DisjoinSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    private int findParent(int node) {
        if(node == parent[node]) {
            return node;
        }    
        return parent[node] = findParent(parent[node]);
    }
    
    public int find(int node) {
        return findParent(node);
    }
    
    public void unionNode(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);
        
        if(parent1 == parent2) {
            return;
        }
        
        if(rank[parent1] > rank[parent2]) {
            parent[parent2] = parent1;
        } else if(rank[parent1] < rank[parent2]) {
            parent[parent1] = parent2;
        } else {
            parent[parent2] = parent1;
            rank[parent1]++;
        }
    }
}

class Solution {
    public int spanningTree(int V, int[][] edges) {
        DisjoinSet dj = new DisjoinSet(V);
        
        Arrays.sort(edges, new Comparator<>(){
            public int compare(int[] edge1, int[] edge2) {
                return Integer.compare(edge1[2], edge2[2]);
            }
        });
        
        int minWeight = 0;
        
        int u, v, wt;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1]; wt = edge[2];
            
            if(dj.find(u) == dj.find(v)) {
                continue;
            }
            
            dj.unionNode(u, v);
            minWeight += wt;
        }
        
        return minWeight;
    }
}
