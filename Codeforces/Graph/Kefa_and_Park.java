package Graph; /**
 * @author Pawan Mehta
 * @email arowpk@gmail.com
 */

import java.io.*;
import java.util.*;

public class Kefa_and_Park {

    public static int count;

    private static void dfs(int node, int parent, int consecutiveCats, int m, int[] cats, ArrayList<ArrayList<Integer>> adjList) {
        if(cats[node] == 1) {
            consecutiveCats++;
        } else {
            consecutiveCats = 0;
        }

        if(consecutiveCats > m) {
            return;
        }

        boolean isLeaf = true;

        for(int adjNode : adjList.get(node)) {
            if(parent != adjNode) {
                dfs(adjNode, node, consecutiveCats, m, cats, adjList);
                isLeaf = false;
            }
        }

        if(isLeaf) {
            count++;
        }
    }

    public static void solve(FastScanner fs, int n,  int m, int[] cats, ArrayList<ArrayList<Integer>> adjList) throws IOException {
        count = 0;

        dfs(0, -1, 0, m, cats, adjList);

        fs.writer().write(count + "\n");
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            int[] cats = fs.readIntArray(n);

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

            solve(fs, n, m, cats, adjList);
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
