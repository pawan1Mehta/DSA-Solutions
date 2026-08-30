package Github_Codeforces_Round_1064_Div2;

import java.io.*;
import java.util.*;


public class Renako_Amaori_and_XOR_Game_easy_version {

    public static void solve(FastScanner fs, int n, int[] a, int[] b) throws IOException {
        int ajisai = 0;
        int mai = 0;
        int revIndex = -1;

        for(int i = 0; i < n; i++) {
            ajisai = ajisai ^ a[i];
            mai = mai ^ b[i];
            if(a[i] != b[i]) {
                revIndex = i + 1;
            }
        }

        if(ajisai == mai) {
            fs.writer().write("Tie \n");
            return;
        }

        if(revIndex%2 != 0) {
            if((ajisai ^ b[revIndex - 1]) > (mai ^ a[revIndex - 1])) {
                ajisai = ajisai ^ b[revIndex - 1];
                mai = mai ^ a[revIndex - 1];
            } else {
                ajisai = ajisai ^ a[revIndex - 1];
                mai = mai ^ b[revIndex - 1];
            }
        } else {
            if((mai ^ a[revIndex - 1]) > (ajisai ^ b[revIndex - 1])) {
                ajisai = ajisai ^ b[revIndex - 1];
                mai = mai ^ a[revIndex - 1];
            } else {
                ajisai = ajisai ^ a[revIndex - 1];
                mai = mai ^ b[revIndex - 1];
            }
        }

        if(ajisai == mai) {
            fs.writer().write("Tie \n");
        } else if(ajisai > mai) {
            fs.writer().write("Ajisai \n");
        } else {
            fs.writer().write("Mai \n");
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int k = fs.nextInt();
        // int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            int[] a = fs.readIntArray(n);
            int[] b = fs.readIntArray(n);

            solve(fs, n, a, b);
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
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

    public  static int MOD = 1_000_000_007;

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
