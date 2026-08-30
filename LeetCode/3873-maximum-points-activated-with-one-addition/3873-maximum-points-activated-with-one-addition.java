class DisjointSet {
    private int[] parent;
    private int[] size;

    public DisjointSet(int n) {
        this.parent = new int[n];
        this.size = new int[n];  

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }    
    }

    private int parentNode(int node) {
        if(parent[node] == node) {
            return node;
        }
        return parent[node] = parentNode(parent[node]);
    }

    public int findParent(int node) {
        return parentNode(node);
    }
    
    public void unionNode(int node1, int node2) {
        int node1Parent = findParent(node1);
        int node2Parent = findParent(node2);

        if(node1Parent == node2Parent) {
            return;
        }

        if(size[node2Parent] < size[node1Parent]) {
            parent[node2Parent] = node1Parent;
            size[node1Parent] += size[node2Parent];
        } else {
            parent[node1Parent] = node2Parent;
            size[node2Parent] += size[node1Parent];
        }
    }

    public int getMaxUnion() {
        int max1 = 0, max2 = 0;

        for(int i = 0; i < parent.length; i++) {
            if(parent[i] == i) {
                int currSize = size[i];
                if(currSize > max1) {
                    max2 = max1;
                    max1 = currSize;
                } else if(currSize > max2) {
                    max2 = currSize;
                }
            }
        }

        return max1 + max2 + 1;
    }
}

class Solution {
    public int maxActivated(int[][] points) {
        DisjointSet ds = new DisjointSet(points.length);

        Map<Integer, Integer> xCordinates = new HashMap<>();
        Map<Integer, Integer> yCordinates = new HashMap<>();

        int x, y;
        for(int i = 0; i < points.length; i++) {
            x = points[i][0]; y = points[i][1];

            if(xCordinates.containsKey(x)) {
                ds.unionNode(i, xCordinates.get(x));
            } else {
                xCordinates.put(x, i);
            }

            if(yCordinates.containsKey(y)) {
                ds.unionNode(i, yCordinates.get(y));
            } else {
                yCordinates.put(y, i);
            }
        }

        return ds.getMaxUnion();
    }
}