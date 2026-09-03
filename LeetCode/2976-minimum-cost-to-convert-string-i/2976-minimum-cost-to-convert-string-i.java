class Solution {
    public static final int MAX_CHARS = 26;

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();

        if(source.equals(target)) {
            return 0L;
        }

        List<List<int[]>> adjList = constructAdjList(original, changed, cost);

        int[][] dist = new int[MAX_CHARS][MAX_CHARS];

        for(int i = 0; i < MAX_CHARS; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<int[]> bfs = new LinkedList<>();

        int src, tgt, ct;
        for(int i = 0; i < n; i++) {
            src = source.charAt(i) - 'a';
            tgt = target.charAt(i) - 'a';
            
            if(src == tgt) {
                dist[src][tgt] = 0;
            } else {
                bfs.add(new int[]{src, src, 0});
            }
        }

        int tgtsrc;
        while(!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            src = curr[0];
            tgtsrc = curr[1];
            ct = curr[2];

            for(int[] adjNode : adjList.get(src)) {
                int dest = adjNode[0];
                int destCost = adjNode[1];

                if(dist[tgtsrc][dest] > (ct + destCost)) {
                    dist[tgtsrc][dest] = ct + destCost;

                    bfs.add(new int[]{dest, tgtsrc, dist[tgtsrc][dest]});
                }
            }
        }
        
        long minCost = 0;
        for(int i = 0; i < n; i++) {
            src = source.charAt(i) - 'a';
            tgt = target.charAt(i) - 'a';
            
            if(dist[src][tgt] == Integer.MAX_VALUE) {
                return -1L;
            }

            minCost += dist[src][tgt];
        }

        return minCost;
    }

    private List<List<int[]>> constructAdjList(char[] original, char[] changed, int[] cost) {
        int n = cost.length;

        List<List<int[]>> adjList = new ArrayList<>();

        for(int i = 0; i < MAX_CHARS; i++) {
            adjList.add(new ArrayList<>());
        }

        int source, target, ct;
        for(int i = 0; i < n; i++) {
            source = original[i] - 'a';
            target = changed[i] - 'a';
            ct = cost[i];

            adjList.get(source).add(new int[]{target, ct});
        }

        return adjList;
    }
}