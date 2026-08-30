package Graph;

import java.io.*;
import java.util.*;

public class BuildingTeams {

    private static boolean bfs(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{srcNode, 1});
        color[srcNode] = 1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int node = curr[0];
            int col = curr[1];

            for(int adjNode : adjList.get(node)) {
                if(color[adjNode] == col) {
                    return false;
                }

                if(color[adjNode] == -1) {
                    color[adjNode] = col == 1 ? 2 : 1;
                    q.add(new int[]{adjNode, color[adjNode]});
                }
            }
        }

        return true;
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

    public static void solve(FastScanner fs, int n, int[][] friendships) throws IOException {
        ArrayList<ArrayList<Integer>> adjList = constructAdjList(n, friendships);

        boolean isPossible = true;

        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int node = 0; node < n; node++) {
            if(color[node] == - 1) {
                if(!bfs(node, adjList, color)) {
                    isPossible = false;
                    break;
                }
            }
        }

        if(!isPossible) {
            fs.writer().write("IMPOSSIBLE\n");
        } else {
            for(int node = 0; node < n; node++) {
                fs.writer().write(color[node] + " ");
            }
            fs.writer().write("\n");
        }
    }

    public  static void main(String[] args) throws Exception {
       FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            int[][] friendships = new int[m][2];
            for(int i = 0; i < m; i++) {
                int[] friend = fs.readIntArray(2);
                friend[0]--; friend[1]--;
                friendships[i] = friend;
            }

            solve(fs, n, friendships);
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
