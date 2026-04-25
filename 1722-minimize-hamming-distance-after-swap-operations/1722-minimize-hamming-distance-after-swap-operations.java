class DisjointSet{
    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

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

    public void unionNode(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);
        
        if(parent1 == parent2) {
            return;
        }

        if(rank[parent1] > rank[parent2]) {
            parent[parent2] = parent1;
        } else if(rank[parent1] < rank[parent2]) {
            parent[parent1] = parent2;
        } else {
            parent[parent1] = parent2;
            rank[parent1]++;
        }
    }
}

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        int maxNum = 1_000_01;

        DisjointSet ds = new DisjointSet(maxNum);

        for(int[] swap : allowedSwaps) {
            ds.unionNode(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> st = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int stPrt = ds.findParent(i);

            st.putIfAbsent(stPrt, new HashMap<>());
            
            Map<Integer, Integer> mp = st.get(stPrt);
            mp.put(source[i], mp.getOrDefault(source[i], 0) + 1);
        }

        int hammingDist = 0;

        for(int i = 0; i < n; i++) {
            int stPrt = ds.findParent(i);

            Map<Integer, Integer> mp = st.get(stPrt);
            
            if(mp.getOrDefault(target[i], 0) > 0) {
                mp.put(target[i], mp.get(target[i]) - 1);
            } else {
                hammingDist++;
            }
        }

        return hammingDist;
    }
}