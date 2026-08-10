class Solution {

    private long height(int node, ArrayList<ArrayList<Integer>> adjList) {
        long height = 0;
        for(int adjNode : adjList.get(node)) {
            height = (long) Math.max(height, height(adjNode, adjList));
        }
        return height + 1;
    }

    private long solve(int node, int depth, long height, ArrayList<ArrayList<Integer>> adjList, int[] nums) {
        long childWeights = 0;
        
        for(int adjNode : adjList.get(node)) {
            childWeights += solve(adjNode, depth + 1, height, adjList, nums);
        }

        return childWeights + (nums[node] * (height - depth + 1));
    }

    public long weightedSum(int[] parent, int[] nums) {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(nums.length, parent);

        long height = height(0, adjList);
        return solve(0, 1, height, adjList, nums);
    }

    private ArrayList<ArrayList<Integer>> constructAdjList(int n, int[] parent) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int node = 1; node < n; node++) {
            int prt = parent[node];
            adjList.get(prt).add(node);
        }

        return adjList;
    }
}