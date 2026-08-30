package Graph.FloydWarshall;

import java.io.*;
import java.util.*;

public class Greg_and_Graph {

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            long[][] dist = new long[n][n];

            for(int i = 0; i < n; i++) {
                long[] wt = fs.readLongArray(n);
                for(int j = 0; j < n; j++) {
                    dist[i][j] = wt[j];
                }
            }

            int[] rmNodes = fs.readIntArray(n);

            for(int i = 0; i < n; i++) {
                rmNodes[i]--;
            }

            for(int i = 0; i < n/2; i++) {
                int temp = rmNodes[i];
                rmNodes[i] = rmNodes[n - i - 1];
                rmNodes[n - i - 1] = temp;
            }

            long[] res = new long[n];

            for(int m = 0; m < n; m++) {
                int intermediate = rmNodes[m];

                for(int srcNode = 0; srcNode < n; srcNode++) {
                    for(int destNode = 0; destNode < n; destNode++) {
                        long sum  = dist[srcNode][intermediate] + dist[intermediate][destNode];
                        if(dist[srcNode][destNode] > sum) {
                            dist[srcNode][destNode] = sum;
                        }
                    }
                }

                long sum = 0;
                for(int i = 0; i <= m; i++) {
                    for(int j = 0; j <= m; j++) {
                        sum += dist[rmNodes[i]][rmNodes[j]];
                    }
                }

                res[m] = sum;
            }

            for(int i = n - 1; i >= 0; i--) {
                fs.writer().write(res[i] + " ");
            }
            fs.writer().write("\n");
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
    public  static int MOD = 1_000_000_007;

    public static void fill(int[][] arr, int num) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }
    public static void fill(long[][] arr, long num) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }
    public static void fill(int[] arr, int num) {
        Arrays.fill(arr, num);
    }
    public static void fill(long[] arr, long num) {
        Arrays.fill(arr, num);
    }

    // ======================================== Math ======================================================
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

    // ======================================== Graph ======================================================
    public  static int[] dr = {-1, 1, 0, 0};
    public  static int[] dc = {0, 0, -1, 1};

    private static boolean bfsColoring(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{srcNode, 1});
        color[srcNode] = 1;

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();

            int currNode = curr[0];
            int currCol = curr[1];

            for(int adjNode : adjList.get(currNode)) {
                if(currCol == color[adjNode]) {
                    return false;
                }

                if(color[adjNode] == -1) {
                    color[adjNode] = currCol == 1 ? 0 : 1;
                    bfs.add(new int[]{adjNode, color[adjNode]});
                }
            }
        }

        return  true;
    }
    private static boolean graphColoring(int n, int[] color, ArrayList<ArrayList<Integer>> adjList) {
        Arrays.fill(color, -1);

        for(int node = 0; node < n; node++) {
            if(color[node] == -1) {
                if(!bfsColoring(node, adjList, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    // ======================================== DEBUG ======================================================
    public static void printArr(int[] arr) {
        int n = arr.length;
        System.out.print("arr: [");
        for(int i = 0; i < n; i++) {
            if (i == n-1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println(" ] \n");
    }

    // ======================================== Input ======================================================
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
