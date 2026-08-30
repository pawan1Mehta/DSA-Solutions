package Graph;

import java.io.*;
import java.util.*;

public class Github_Cycle_In_Maze {

    private static boolean isValid(int r, int c, int n, int m) {
        if(r >= 0 && r < n && c >= 0 && c < m) {
            return true;
        }
        return false;
    }

    public static void solve(FastScanner fs, int n, int m, char[][] maze, int k) throws IOException {
        if (k % 2 != 0) {
            fs.writer().write("IMPOSSIBLE \n");
            return;
        }

        int srci = 0, srcj = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 'X') {
                    srci = i;
                    srcj = j;
                    maze[i][j] = '.';
                    break;
                }
            }
        }

        Queue<int[]> bfs = new LinkedList<>();
        int[][] dist = new int[n][m];

        fill(dist, -1);

        bfs.add(new int[]{srci, srcj, 0});
        dist[srci][srcj] = 0;

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int r = curr[0];
            int c = curr[1];
            int dt = curr[2];

            for(int num = 0; num < 4; num++) {
                int nr = r + dr[num];
                int nc = c + dc[num];

                if(isValid(nr, nc, n, m) && maze[nr][nc] != '*' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dt + 1;
                    bfs.add(new int[]{nr, nc, dist[nr][nc]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = srci, j = srcj;
        while (k > 0) {
            if(isValid(i + 1, j, n, m) && dist[i + 1][j] != -1 && dist[i + 1][j] <= k - 1) {
                sb.append('D');
                i++;
                k--;
            } else if(isValid(i, j - 1, n, m) && dist[i][j - 1] != -1 && dist[i][j - 1] <= k - 1) {
                sb.append('L');
                j--;
                k--;
            } else if(isValid(i, j + 1, n, m) && dist[i][j + 1] != -1 && dist[i][j + 1] <= k - 1) {
                sb.append('R');
                j++;
                k--;
            } else if(isValid(i - 1, j, n, m) && dist[i - 1][j] != -1 && dist[i - 1][j] <= k - 1) {
                sb.append('U');
                i--;
                k--;
            } else {
                fs.writer().write("IMPOSSIBLE \n");
                return;
            }
        }

        fs.writer().write( sb + " \n");
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
                String row = fs.nexString();
                for(int j = 0; j < m; j++) {
                    maze[i][j] = row.charAt(j);
                }
            }

            solve(fs, n, m, maze, kk);
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
    public  static int MOD = 1_000_000_007;

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

    public static void fill(int[][] arr, int num) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }
    public  static void fill(int[] arr, int num) {
        Arrays.fill(arr, num);
    }

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