package Codeforces_Round_1076_Div3;

import java.io.*;
import java.util.*;

public class Problem3 {

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

            tree[v] = tree[2 * v] + tree[2 * v + 1];
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

            tree[v] = tree[2 * v] + tree[2 * v + 1];
        }

        private long sum(int v, int tl, int tr, int ql, int qr) {
            if(ql > qr) {
                return 0;
            }

            if(tl >= ql && tr <= qr) {
                return tree[v];
            }

            int tmid = (tl + tr)/2;

            long leftSum = sum(2 * v, tl, tmid, ql, Math.min(tmid, qr));
            long rightSum = sum(2 * v + 1, tmid + 1, tr, Math.max(tmid + 1, ql), qr);

            return leftSum + rightSum;
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        public long sum(int ql, int qr) {
            return sum(1, 0, n - 1, ql, qr);
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int k = fs.nextInt();
        // int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int q = fs.nextInt();

            int[] a = fs.readIntArray(n);
            int[] b = fs.readIntArray(n);

            for(int i = n-2; i >= 0; i--) {
                b[i] = Math.max(b[i], b[i + 1]);
            }

            for(int i = n - 1; i >= 0; i--) {
                a[i] = Math.max(a[i], Math.max((i + 1 < n ? a[i + 1] : 0), b[i]));
            }

            SegmentTree st = new SegmentTree(n, a);

            int l, r;
            for(int i = 0; i < q; i++) {
                l = fs.nextInt() - 1;
                r = fs.nextInt() - 1;

                long sum = st.sum(l, r);

                fs.writer().write(sum + " ");
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

    /*
            Binary exponentiation is a method to calculate powers like a^n efficiently.

            Instead of multiplying a by itself n−1 times (which takes a long time when n is large),
            binary exponentiation uses a smarter approach that only requires about log n multiplications.

            Binary exponentiation makes use of the binary representation of the exponent n to break the
            problem into smaller parts.

            a^13
                we can write 13 =  1101 base 2 in binary representation
                which means  2^3 + 2^2 + 2^0 (8 + 4 + 1)

                so instend of multiplications, we can calculate a^8 + a^2 + a^0
   */
    public static long binaryExponentiation(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if((b & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
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
