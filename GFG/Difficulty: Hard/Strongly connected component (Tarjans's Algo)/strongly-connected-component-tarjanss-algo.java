// User function Template for Java

class Solution {
    private static int discTime = 0;
    
    private void tarjansUtil(int node, ArrayList<ArrayList<Integer>> adjList, int[] disc, int[] low, boolean[] visited, Stack<Integer> st, ArrayList<ArrayList<Integer>> resList) {
        st.add(node);
        disc[node] = low[node] = discTime++;
        visited[node] = true;
        
        for(int adjNode : adjList.get(node)) {
            if(disc[adjNode] == -1) {
                tarjansUtil(adjNode, adjList, disc, low, visited, st, resList);
                low[node] = Math.min(low[node], low[adjNode]);
            } else if(visited[adjNode]) {
                low[node] = Math.min(low[node], disc[adjNode]);
            }
        }
        
        if(disc[node] == low[node]) {
            ArrayList<Integer> res = new ArrayList<>();
            
            int tempNode = -1;
            while(tempNode != node) {
                tempNode = st.pop();
                res.add(tempNode);
                visited[tempNode] = false;
            }
            
            Collections.sort(res);
            resList.add(res);
        }
    }
    
    public ArrayList<ArrayList<Integer>> tarjans(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(visited, false);
        
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        
        discTime = 0;
        
        for(int node = 0; node < V; node++) {
            if(disc[node] == -1) {
                tarjansUtil(node, adj, disc, low, visited, st, resList);
            }
        }
        
        Collections.sort(resList, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                int minLen = Math.min(a.size(), b.size());
                for (int i = 0; i < minLen; i++) {
                    if (a.get(i) != b.get(i)) {
                        return Integer.compare(a.get(i), b.get(i));
                    }
                }
                return Integer.compare(a.size(), b.size());
            }
        });
        
        return resList;
    }
}