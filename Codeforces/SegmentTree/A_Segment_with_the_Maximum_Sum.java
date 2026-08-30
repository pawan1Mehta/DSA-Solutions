package SegmentTree;

import java.io.*;
import java.util.*;

public class A_Segment_with_the_Maximum_Sum {

    public static class SegmentTree {
        Node[] tree;
        int n;

        static class Node {
            long maxSubSegment;
            long maxPrefixSum;
            long maxSuffixSum;
            long sum;

            public Node(long maxSubSegment, long maxPrefixSum, long maxSuffixSum, long sum) {
                this.maxSubSegment = maxSubSegment;
                this.maxPrefixSum = maxPrefixSum;
                this.maxSuffixSum = maxSuffixSum;
                this.sum = sum;
            }
        }

        private Node single(long val) {
            return new Node(
                    Math.max(0, val),
                    Math.max(0, val),
                    Math.max(0, val),
                    val
            );
        }

        private Node merge(Node node1, Node node2) {
            return new Node(
                    Math.max(Math.max(node1.maxSubSegment, node2.maxSubSegment), node1.maxSuffixSum + node2.maxPrefixSum),
                    Math.max(node1.maxPrefixSum, node1.sum + node2.maxPrefixSum),
                    Math.max(node2.maxSuffixSum, node1.maxSuffixSum + node2.sum),
                    node1.sum + node2.sum
            );
        }

        public SegmentTree(int n, int[] arr) {
            this.n = n;
            this.tree = new Node[4 * n];
            buildTree(1, 0, n - 1, arr);
        }

        private void buildTree(int v, int tl, int tr, int[] arr) {
            if(tl == tr) {
                tree[v] = single(arr[tl]);
                return;
            }

            int tmid = (tl + tr)/2;

            buildTree(2 * v, tl, tmid, arr);
            buildTree(2 * v + 1, tmid + 1, tr, arr);

            tree[v] = merge(tree[2 * v], tree[2 * v + 1]);
        }

        private void update(int v, int tl, int tr, int index, int val) {
            if(tl > tr) {
                return;
            }

            if(tl == tr) {
                tree[v] = single(val);
                return;
            }

            int tmid = (tl + tr)/2;

            if(index <= tmid) {
                update(2 * v, tl, tmid, index, val);
            } else {
                update(2 * v + 1, tmid + 1, tr, index, val);
            }

            tree[v] = merge(tree[2 * v], tree[2 * v + 1]);
        }

        private Node getMaxSumSegment(int v, int tl, int tr, int ql, int qr) {
            if(ql > qr) {
                return single(Integer.MAX_VALUE);
            }

            if(tl >= ql && tr <= qr) {
                return tree[v];
            }

            int tmid = (tl + tr)/2;

            Node leftMax = getMaxSumSegment(2 * v, tl, tmid, ql, Math.min(tmid , qr));
            Node rightMax = getMaxSumSegment(2 * v + 1, tmid + 1, tr, Math.max(tmid + 1, ql), qr);

            return merge(leftMax, rightMax);
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        public Node getMaxSumSegment(int ql, int qr) {
            return getMaxSumSegment(1, 0, n - 1, ql, qr);
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

            fs.writer().write(st.getMaxSumSegment(0, n - 1).maxSubSegment + "\n");
            int[] q;
            for(int i = 0; i < m; i++) {
                q = fs.readIntArray(2);
                st.update(q[0], q[1]);
                fs.writer().write(st.getMaxSumSegment(0, n - 1).maxSubSegment + "\n");
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
