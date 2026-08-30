package SegmentTree;

import java.io.*;
import java.util.*;

public class Segment_Tree_for_the_Minimum {

    public static class SegmentTree {

        int n;
        long[] tree;

        public SegmentTree(int n, int[] nums) {
            this.n = n;
            this.tree = new long[4 * n];
            buildTree(1, 0, n - 1, nums);
        }

        private void buildTree(int v, int tl, int tr, int[] nums) {
            if(tl == tr) {
                tree[v] = nums[tl];
                return;
            }

            int tmid = (tl + tr)/2;

            buildTree(2 * v, tl, tmid, nums);
            buildTree(2 * v + 1, tmid + 1, tr, nums);

            tree[v] = Math.min(tree[2 * v], tree[2 * v + 1]);
        }

        private void update(int v, int tl, int tr, int index, int val) {
            if(tl > tr) {
                return;
            }

            if(tl == tr) {
                tree[v] = val;
                return;
            }

            int tmid = (tl + tr)/2;

            if(index <= tmid) {
                update(2 * v, tl, tmid, index, val);
            } else {
                update(2 * v + 1, tmid + 1, tr, index, val);
            }

            tree[v] = Math.min(tree[2 * v], tree[2 * v + 1]);
        }

        private long min(int v, int tl, int tr, int ql, int qr) {
            if(ql > qr) {
                return Integer.MAX_VALUE;
            }

            if(tl >= ql && tr <= qr) {
                return tree[v];
            }

            int tmid = (tl + tr)/2;

            long leftMin = min(2 * v, tl, tmid, ql, Math.min(tmid, qr));
            long rightMin = min(2 * v + 1, tmid + 1, tr, Math.max(tmid + 1, ql), qr);

            return Math.min(leftMin, rightMin);
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        public long min(int ql, int qr) {
            return min(1, 0, n - 1, ql, qr);
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            int[] nums = fs.readIntArray(n);

            SegmentTree st = new SegmentTree(n, nums);

            int a, b, c;
            for(int i = 0; i < m; i++) {
                a = fs.nextInt();
                b = fs.nextInt();
                c = fs.nextInt();

                if(a == 1) {
                    st.update(b, c);
                } else {
                    fs.writer().write(st.min(b, c - 1) + "\n");
                }
            }

        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
    public  static int MOD = 1_000_000_007;

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

    public static void fill(int[][] arr, int num) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }
    public  static void fill(int[] arr, int num) {
        Arrays.fill(arr, num);
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
