package Graph;

import java.io.*;
import java.util.*;

public class Github_Maze {
    public static int needVisit;

    private static boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static void dfs(int i, int j, char[][] maze, int n, int  m) {
        if(isValid(i, j, n, m) && needVisit > 0 && maze[i][j] == '.') {
            needVisit--;

            maze[i][j] = 'O';

            dfs(i, j + 1, maze, n, m);
            dfs(i, j - 1, maze, n, m);
            dfs(i + 1, j , maze, n, m);
            dfs(i - 1, j, maze, n, m);
        }
    }

    public static void solve(FastScanner fs, int n, int m, char[][] maze, int k) throws IOException {
        int emptyCells = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == '.') {
                    emptyCells++;
                }
            }
        }

        needVisit = emptyCells - k;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == '.' && needVisit > 0) {
                    dfs(i, j, maze, n, m);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == '.') {
                    maze[i][j] = 'X';
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 'O') {
                    maze[i][j] = '.';
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                fs.writer().write(maze[i][j] + "");
            }
            fs.writer().write("\n");
        }

        fs.writer().write("\n");
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            int kk = fs.nextInt();

            char[][] maze = new char[n][m];

            for(int i = 0; i < n; i++) {
                String inp = fs.nexString();
                for(int j = 0; j < m; j++) {
                    maze[i][j] = inp.charAt(j);
                }
            }

            solve(fs, n, m, maze, kk);
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
