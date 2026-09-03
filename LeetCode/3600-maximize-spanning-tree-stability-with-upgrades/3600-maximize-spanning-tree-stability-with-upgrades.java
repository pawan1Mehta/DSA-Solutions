class DisjoinSet{
    int[] parent;
    int[] rank;

    public DisjoinSet(int n) {
        this.parent = new int[n + 1];
        this.rank = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public DisjoinSet(int[] parent, int[] rank) {
        this.parent = parent.clone();
        this.rank = rank.clone();
    }

    private int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }

    public int[] getParent() {
        return this.parent;
    }

    public int[] getRank() {
        return this.rank;
    }

    public int findParentNode(int node) {
        return findParent(node);
    }

    public int unionNode(int node1, int node2) {
        int node1Parent = findParentNode(node1);
        int node2Parent = findParentNode(node2);

        if(node1Parent == node2Parent) {
            return -1;
        }

        if(rank[node1Parent] < rank[node2Parent]) {
            parent[node1Parent] = node2Parent;
        } else if(rank[node1Parent] > rank[node2Parent]) {
            parent[node2Parent] = node1Parent;
        } else {
            parent[node2Parent] = node1Parent;
            rank[node1Parent]++;
        }

        return 0;
    }
}

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int MAX_STABILITY = 200000;

        int maxPosStability = MAX_STABILITY;

        DisjoinSet ds = new DisjoinSet(n);

        List<int[]> mustIncludeEdge = new ArrayList<>();
        List<int[]> optionalEdge = new ArrayList<>();
        
        int u, v, s, must;

        for(int[] edge : edges) {
            must = edge[3];

            if(must == 1) {
                mustIncludeEdge.add(edge);
            } else {
                optionalEdge.add(edge);
            }
        }

        if(mustIncludeEdge.size() > n - 1) {
            return -1;
        }

        int selectedNodes = 0;

        for(int[] edge : mustIncludeEdge) {
            u = edge[0]; v = edge[1]; s = edge[2];

            if(ds.unionNode(u, v) == -1 || selectedNodes == n-1) {
                return -1;
            }

            selectedNodes++;
            maxPosStability = Math.min(maxPosStability, s);
        }

        Collections.sort(optionalEdge, new Comparator<int[]>(){
            public int compare(int[] u, int[] v) {
                return Integer.compare(v[2], u[2]);
            }
        });

        int low = 0, high = maxPosStability;

        int ans = -1;

        while(low < high) {
            int mid = low + (high - low + 1) / 2;

            DisjoinSet nds = new DisjoinSet(ds.getParent(), ds.getRank());
            int newSelected = selectedNodes;
            int doubleCount = 0;

            for(int[] edge : optionalEdge) {
                u = edge[0]; v = edge[1]; s = edge[2];

                if(nds.unionNode(u, v) == -1) {
                    continue;
                }

                if(s >= mid) {
                    newSelected++;
                } else if(doubleCount < k && ((2 * s) >= mid)) {
                    newSelected++;
                    doubleCount++;
                } else {
                    break;
                }

                if(newSelected == n-1) {
                    break; 
                }
            }

            if(newSelected != n-1) {
                high = mid - 1;
            } else {
                ans = low = mid;
            }
        }
        
        return ans;
    }
}