class Solution {

    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, Set<Integer> supiciousMethods) {
        if(supiciousMethods.contains(node)) {
            return;
        }

        supiciousMethods.add(node);

        for(int adjNode : adjList.get(node)) {
            dfs(adjNode, adjList, supiciousMethods);
        }
    }

    private boolean canInvokesSuspiciousMethods(
        int node, 
        ArrayList<ArrayList<Integer>> adjList, 
        Set<Integer> supiciousMethods,
        Set<Integer> visited) {
        
        if(supiciousMethods.contains(node)) {
            return true;
        }

        if(visited.contains(node)) {
            return false;
        }

        visited.add(node);

        for(int adjNode : adjList.get(node)) {
            if(canInvokesSuspiciousMethods(adjNode, adjList, supiciousMethods, visited)) {
                return true;
            }
        }

        return false;
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(n, invocations);

        Set<Integer> supiciousMethods = new HashSet<>();
        dfs(k, adjList, supiciousMethods);

        boolean canInvSusMeth = false;
        Set<Integer> visited = new HashSet<>();
        for(int node = 0; node < n; node++) {
            if(!visited.contains(node) && !supiciousMethods.contains(node)) {
                if(canInvokesSuspiciousMethods(node, adjList, supiciousMethods, visited)) {
                    canInvSusMeth = true;
                    break;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        if(canInvSusMeth) {
            for(int node = 0; node < n; node++) {
                res.add(node);
            }
        } else {
            for(int node = 0; node < n; node++) {
                if(!supiciousMethods.contains(node)) {
                    res.add(node);
                }
            }
        }

        return res;
    }

    private ArrayList<ArrayList<Integer>> constructAdjList(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v;
        for(int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            adjList.get(u).add(v);
        }

        return adjList;
    }
}