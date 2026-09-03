class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    } 

    public int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }

    public void unionNode(int node1, int node2) {
        int prt1 = findParent(node1);
        int prt2 = findParent(node2);

        if(prt1 == prt2) {
            return;
        }

        if(rank[prt1] > rank[prt2]) {
            parent[prt2] = prt1;
        } else if(rank[prt1] < rank[prt2]) {
            parent[prt1] = prt2;
        } else {
            parent[prt2] = prt1;
            rank[prt1]++;
        }
    }
}

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < n - 1; i++) {
            if(Math.abs(nums[i] - nums[i + 1]) <= maxDiff) {
                uf.unionNode(i, i + 1);
            }
        }

        int m = queries.length;
        boolean[] exists = new boolean[m];

        for(int i = 0; i < m; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int prt1 = uf.findParent(u);
            int prt2 = uf.findParent(v);
            
            if(prt1 == prt2) {
                exists[i] = true;
            }
        }

        return exists;
    }
}