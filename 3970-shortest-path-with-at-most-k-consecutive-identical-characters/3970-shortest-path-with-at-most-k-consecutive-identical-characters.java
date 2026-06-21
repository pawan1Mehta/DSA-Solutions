
class Solution {
    static class State {
        int node;
        long dist;
        int conIdentChsCount;

        public State(int node, long dist, int conIdentChsCount) {
            this.node = node;
            this.dist = dist;
            this.conIdentChsCount = conIdentChsCount;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v, w;
        for(int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            w = edge[2];
            adjList.get(u).add(new int[]{v, w});
        }

        PriorityQueue<State> minHeap = 
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        long[][] dist = new long[n][k + 1];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        minHeap.add(new State(0, 0, 1));
        dist[0][1] = 0;

        while(!minHeap.isEmpty()) {
            State curr = minHeap.poll();

            u = curr.node;
            long dt = curr.dist;
            int conIdentChsCount = curr.conIdentChsCount;

            if(dt != dist[u][conIdentChsCount]) continue;

            if(u == n - 1) {
                return (int) dt;
            }

            for(int[] edge : adjList.get(u)) {
                v = edge[0];
                int wt = edge[1];

                int newConIdentChsCount = (labels.charAt(u) == labels.charAt(v)) ? conIdentChsCount + 1: 1;

                if(newConIdentChsCount > k) continue;

                long newDt = dt + wt;

                if(dist[v][newConIdentChsCount] > newDt) {
                    dist[v][newConIdentChsCount] = newDt;
                    minHeap.add(new State(v, newDt, newConIdentChsCount));
                }
            }
        }

        return -1;
    }
}