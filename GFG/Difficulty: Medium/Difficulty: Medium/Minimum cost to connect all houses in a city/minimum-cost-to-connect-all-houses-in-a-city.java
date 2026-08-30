
class DisjointSet {
    int[] parent;
    int[] rank;
    
    public DisjointSet(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }
    
    public boolean unionNode(int node1, int node2) {
        int node1Parent = findParent(node1);
        int node2Parent = findParent(node2);
        
        if(node1Parent == node2Parent) {
            return false;
        }
        
        if(rank[node1Parent] > rank[node2Parent]) {
            parent[node2Parent] = node1Parent;
        } else if(rank[node1Parent] > rank[node2Parent]) {
            parent[node1Parent] = node2Parent;
        } else {
            parent[node2Parent] = node1Parent;
            rank[node1Parent]++;
        }
        
        return true;
    }
}

class Solution {

    public int minCost(int[][] houses) {
        int n = houses.length;
        
        DisjointSet ds = new DisjointSet(1001);
        
        ArrayList<int[]> edges = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int x1 = houses[i][0]; int y1 = houses[i][1];
                int x2 = houses[j][0]; int y2 = houses[j][1];
                
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                
                edges.add(new int[]{dist, i, j});
            }
        }
        
        Collections.sort(edges, new Comparator<int[]>(){
           public int compare(int[] a, int[] b) {
               return Integer.compare(a[0], b[0]);
           } 
        });
        
        int totalCost = 0;
        int count = 0;
        
        int x, y, cost;
        for(int[] edge : edges) {
            cost = edge[0];
            x = edge[1];
            y = edge[2];
            
            if(ds.findParent(x) != ds.findParent(y)) {
                ds.unionNode(x, y);
                totalCost += cost;
                count++;
            }
            
            if(count == n - 1) {
                return totalCost;
            }
        }
        
        return 0;
    }
}
