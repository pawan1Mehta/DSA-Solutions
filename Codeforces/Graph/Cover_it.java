package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cover_it {

    public static void solve(FastScanner fs, int n, ArrayList<ArrayList<Integer>> adjList) throws IOException {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        ArrayList<Integer> evenList = new ArrayList<>();
        ArrayList<Integer> oddList = new ArrayList<>();

        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{0, 0});
        evenList.add(0);
        visited[0] = true;

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();

            int u = curr[0];
            int dt = curr[1];

            for(int v : adjList.get(u)) {
                if(!visited[v]) {
                    dist[v] = dt + 1;

                    bfs.add(new int[]{v, dist[v]});

                    if(dist[v]%2 == 0) {
                        evenList.add(v);
                    } else {
                        oddList.add(v);
                    }

                    visited[v] = true;
                }
            }
        }

        if(evenList.size() < oddList.size()) {
            fs.writer().write(evenList.size() + "\n");
            for (int node : evenList) {
                fs.writer().write((node + 1) + " ");
            }
        } else {
            fs.writer().write(oddList.size() + "\n");
            for (int node : oddList) {
                fs.writer().write((node + 1) + " ");
            }
        }

        fs.writer().write("\n");
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
