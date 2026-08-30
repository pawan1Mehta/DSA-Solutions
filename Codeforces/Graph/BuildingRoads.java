package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


class BuildingRoads {

    public static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, ArrayList<Integer> nodes) {
        visited[node] = true;
        nodes.add(node);

        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adjList, visited, nodes);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> constructAdjList(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

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

    public static void solve(FastScanner fs, int cities, int[][] roads) throws IOException {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(cities, roads);

        ArrayList<ArrayList<Integer>> comp = new ArrayList<>();
        int numComp = 0;

        boolean[] visited = new boolean[cities];
        for(int city  = 0; city < cities; city++) {
            if(!visited[city]) {
                ArrayList<Integer> nodes = new ArrayList<>();
                dfs(city, adjList, visited, nodes);
                numComp++;
                comp.add(nodes);
            }
        }

        if(numComp <= 1) {
            fs.writer().write("0");
        } else {
             fs.writer().write(--numComp + "\n");
             for(int i = 1; i < comp.size(); i++) {
                 fs.writer().write((comp.get(i - 1).get(0) + 1) + " " + (comp.get(i).get(0) + 1) + "\n");
             }
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            int[][] roads = new int[m][2];
            for(int i = 0; i < m; i++) {
                int[] road = fs.readIntArray(2);
                road[0]--; road[1]--;
                roads[i] = road;
            }

            solve(fs, n, roads);
        }

        fs.close();
    }

    public  static int MOD = 1_000_000_007;

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer("");

        String next() {
            while (!stk.hasMoreTokens()) {
                try {
                    stk = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return stk.nextToken();
        }

        BufferedWriter writer() {
            return bw;
        }

        String nexString() {
            return next();
        }

        String readLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        void close() {
            try {
                bw.flush();
                bw.close();
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}