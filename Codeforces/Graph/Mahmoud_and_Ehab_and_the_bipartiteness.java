package Graph;

import java.io.*;
import java.util.*;

public class Mahmoud_and_Ehab_and_the_bipartiteness {

    private static void bfs(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        Queue<int[]> q = new LinkedList<>();

        color[srcNode] = 1;
        q.add(new int[]{srcNode, color[srcNode]});

        while (!q.isEmpty()) {
            int[] currNode = q.poll();

            int node = currNode[0];
            int col = currNode[1];

            for(int adjNode : adjList.get(node)) {
//                if(color[adjNode] == col) {
//                    continue;
//                }

                if(color[adjNode] == -1) {
                    color[adjNode] = col == 1 ? 0 : 1;
                    q.add(new int[]{adjNode, color[adjNode]});
                }
            }
        }
    }

    public static void solve(FastScanner fs, int n, ArrayList<ArrayList<Integer>> adjList) throws IOException {
        int[] color = new int[n];

        Arrays.fill(color, -1);

        for(int node = 0; node < n; node++) {
            if(color[node] == -1) {
                bfs(node, adjList, color);
            }
        }

        Set<Map.Entry<Integer, Integer>> st = new HashSet<>();
        for(int u = 0; u < n; u++) {
            for(int v : adjList.get(u)) {
                st.add(new AbstractMap.SimpleEntry<>(u, v));
            }
        }

        int ones = 0, zeros = 0;
        for(int c : color) {
            if(c == 1) {
                ones++;
            } else {
                zeros++;
            }
        }

        long maxEdge = (long) ones * zeros - (n - 1);

        fs.writer().write(maxEdge + "\n");
    }

    public  static void main(String[] args) throws Exception {
       FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            int u, v;
            for(int i = 0; i < n - 1; i++) {
                int[] edge = fs.readIntArray(2);
                u = edge[0] - 1; v = edge[1] - 1;
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            solve(fs, n, adjList);
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
