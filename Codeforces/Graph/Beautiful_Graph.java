package Graph;

import java.io.*;
import java.util.*;

public class Beautiful_Graph {

    public static int[] count;

    private static boolean isBipartiteGraph(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        color[srcNode] = 0;

        count[0]++;

        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{srcNode, 0});

        while (!bfs.isEmpty()) {
            int[] currNode = bfs.poll();

            int node = currNode[0];
            int col = currNode[1];

            for(int adjNode : adjList.get(node)) {
                if(color[adjNode] == col) {
                    return false;
                }

                if(color[adjNode] == -1) {
                    color[adjNode] = col == 0 ? 1 : 0;

                    if(color[adjNode] == 0) {
                        count[0]++;
                    } else {
                        count[1]++;
                    }

                    bfs.add(new int[]{adjNode, color[adjNode]});
                }
            }
        }

        return true;
    }

    public static void solve(FastScanner fs, int n, ArrayList<ArrayList<Integer>> adjList) throws IOException {
        /*
            Option 1: Odd on side A (a vertices, 2 choices each = 2a), even on B (1 choice each = 1b).
            Option 2: Swap (2b + 1a).
            Total: 2a + 2b.
        * */

        int MOD = 998244353;

        power(2, n, MOD);

        int[] color = new int[n];

        Arrays.fill(color, -1);

        long res = 1;

        for(int node = 0; node < n; node++) {
            if(color[node] == -1) {
                count =  new int[]{0, 0};

                if(!isBipartiteGraph(node, adjList, color)) {
                    fs.writer().write("0 \n");
                    return;
                }

                long curr = (p[count[0]] + p[count[1]]) % MOD;
                res = (res * curr) % MOD;
            }
        }

        fs.writer().write(res + "\n");
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int k = fs.nextInt();
        // int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            int u, v;
            for(int i = 0; i < m; i++) {
                int[] edge = fs.readIntArray(2);
                u = edge[0] - 1; v = edge[1] - 1;
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            solve(fs, n, adjList);
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
    public  static int MOD = 1_000_000_007;

    public static long[] p;
    public static void power(int num, int n, long mod) {
        p = new long[n + 1];
        p[0] = 1;
        for(int i = 1; i <= n; i++) {
            p[i] = (num * p[i - 1]) % mod;
        }
    }
    public static void power(int num, int n) {
        p = new long[n + 1];
        p[0] = 1;
        for(int i = 1; i <= n; i++) {
            p[i] = (num * p[i - 1]);
        }
    }

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
