class Solution {

    private int dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] isRemoved, int[] value) {
        int sum = value[node];

        for(int adjNode : adjList.get(node)) {
            sum += dfs(adjNode, adjList, isRemoved, value);
        }

        if(sum == 0) {
            isRemoved[node] = true;
        }

        return sum;
    }
    
    private int dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] isRemoved) {
        if(isRemoved[node]) {
            return 0;
        }

        int count = 1;

        for(int adjNode : adjList.get(node)) {
            count += dfs(adjNode, adjList, isRemoved);
        }

        return count;
    }

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(nodes, parent);

        boolean[] isRemoved = new boolean[nodes];

        dfs(0, adjList, isRemoved, value);

        return dfs(0, adjList, isRemoved);
    }

    private ArrayList<ArrayList<Integer>> constructAdjList(int nodes, int[] parent) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int node = 1; node < nodes; node++) {
            adjList.get(parent[node]).add(node);
        }

        return adjList;
    }
}