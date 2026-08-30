class Solution {
    private void dfs(int node, List<List<Integer>> adjList, boolean[] visited, Map<Integer, TreeSet<Integer>> cmpMp, Map<Integer, Integer> nodeMp, int component) {
        visited[node] = true;
        
        if(!cmpMp.containsKey(component)) {
            cmpMp.put(component, new TreeSet<>());
        }

        cmpMp.get(component).add(node);
        nodeMp.putIfAbsent(node, component);

        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adjList, visited, cmpMp, nodeMp, component);
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        Map<Integer, TreeSet<Integer>> cmpMp = new HashMap<>();
        Map<Integer, Integer> nodeMp = new HashMap<>();

        List<List<Integer>> adjList = constructAdjList(c + 1, connections);

        boolean[] visited = new boolean[c + 1];
        int component = 1;

        for(int i = 1; i <= c; i++) {
            if(!visited[i]) {
                dfs(i, adjList, visited, cmpMp, nodeMp, component);
                component++;
            }
        }

        List<Integer> resList = new ArrayList<>();

        int cmp, x,node;
        for(int [] query : queries) {
            x = query[0]; node = query[1];
            if(x == 1) {
                cmp = nodeMp.get(node);
                if(cmpMp.get(cmp).size() > 0) {
                    if(cmpMp.get(cmp).contains(node)) {
                        resList.add(node);
                    } else {
                        resList.add(cmpMp.get(cmp).first());
                    }
                } else {
                    resList.add(-1);
                }
            } else {
                cmp = nodeMp.get(node);
                if(cmpMp.get(cmp).contains(node)) {
                    cmpMp.get(cmp).remove(node);
                }
            }
        }

        int[] res = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }

    private List<List<Integer>> constructAdjList(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v;
        for(int[] edge : edges) {
            u = edge[0]; v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}