package Graph;

import java.io.*;
import java.util.*;

public class Github_Kings_Path {

    private static boolean isPossible(int nr, int nc, Set<Long> allowedCells) {
        return allowedCells.contains(encodeToLong(nr, nc));
    }

    public static void solve(FastScanner fs, int x0, int y0, int x1, int y1, int n, ArrayList<int[]> segs) throws IOException {
        Set<Long> allowedCells = new HashSet<>();

        int r, a, b;
        for(int[] inp : segs) {
            r = inp[0]; a = inp[1]; b = inp[2];

            for(int col = a; col <= b; col++) {
                allowedCells.add(encodeToLong(r, col));
            }
        }

        int[][] nextDir = new int[][]{
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {0, 1},
                {1, -1},
                {1, 0},
                {1, 1}
        };

        Set<Long> visited = new HashSet<>();
        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{x0, y0, 0});
        visited.add(encodeToLong(x0, y0));

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int x = curr[0];
            int y = curr[1];
            int dt = curr[2];

            if(x == x1 && y == y1) {
                fs.writer().write(dt + " \n");
                return;
            }

            for(int[] dir : nextDir) {
                int nr = x + dir[0];
                int nc = y + dir[1];

                if(isPossible(nr, nc, allowedCells) && !visited.contains(encodeToLong(nr, nc))) {
                    visited.add(encodeToLong(nr, nc));
                    bfs.add(new int[]{nr, nc, dt + 1});
                }
            }
        }

        fs.writer().write("-1 \n");
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int x0 = fs.nextInt(); int y0 = fs.nextInt();
            int x1 = fs.nextInt(); int y1 = fs.nextInt();

            int n = fs.nextInt();

            ArrayList<int[]> segs = new ArrayList<>();

            int r, a, b;
            for(int i = 0; i < n; i++) {
                int[] inp = fs.readIntArray(3);
                r = inp[0]; a = inp[1]; b = inp[2];
                segs.add(new int[]{r, a, b});
            }

            solve(fs, x0, y0, x1, y1, n, segs);
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================

    public static long encodeToLong(int a, int b) {
        return ((long) a << 32) | b;
    }

    public static boolean bfsColoring(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
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

    public static boolean graphColoring(int n, int[] color, ArrayList<ArrayList<Integer>> adjList) {
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
