package BinaryLifting;

import java.io.*;
import java.util.*;

public class Sloth_Naptime {

    static class Node {
        int depth;
        ArrayList<Node> adjList = new ArrayList<>();
        Node[] up = new Node[20];
        int id;

        public Node(int id) {
            this.id = id;
        }

        public void dfs(Node parent, int depth) {
            this.depth = depth;
            this.up[0] = parent;

            for(Node adjNode : adjList) {
                if(adjNode != parent) {
                    adjNode.dfs(this, depth + 1);
                }
            }
        }

        public Node goUp(int nSteps) {
            if(nSteps == 0) {
                return this;
            }

            int largestPower = 1;
            int k = 0;
            while (2 * largestPower  <= nSteps) {
                largestPower = 2 * largestPower;
                k++;
            }

            Node nextNode = this.up[k];

            return nextNode.goUp(nSteps - largestPower);
        }

        public Node lca(Node b, int maxJumps) {
            if(this == b) {
                return this;
            }

            if(this.depth != b.depth) {
                if(this.depth > b.depth) {
                    return this.goUp(this.depth - b.depth).lca(b, 19);
                } else {
                    return this.lca(b.goUp(b.depth - this.depth), 19);
                }
            }

            if(this.up[0] == b.up[0]) {
                return this.up[0];
            }

            while (this.up[maxJumps] == b.up[maxJumps]) {
                maxJumps--;
            }

            return this.up[maxJumps].lca(b.up[maxJumps], maxJumps);
        }

        public String toString() {
            return String.valueOf(id);
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            Node[] nodes = new Node[n];

            for(int i = 0; i < n; i++) {
                nodes[i] = new Node(i + 1);
            }

            int u, v;
            for(int i = 0; i < n-1; i++) {
                u = fs.nextInt() - 1;
                v = fs.nextInt() - 1;

                nodes[u].adjList.add(nodes[v]);
                nodes[v].adjList.add(nodes[u]);
            }

            nodes[0].dfs(null, 0);

            for(int p = 1; p < 20; p++) {
                for(Node node : nodes) {
                    if(node.up[p - 1] != null) {
                        node.up[p] = node.up[p - 1].up[p - 1];
                    }
                }
            }

            int q = fs.nextInt();
            int a,b,c;
            for(int i = 0; i < q; i++) {
                a = fs.nextInt() - 1;
                b = fs.nextInt() - 1;
                c = fs.nextInt();

                Node nodeA = nodes[a];
                Node nodeB = nodes[b];

                Node lcaNode = nodeA.lca(nodeB, 19);

                int pathLength = nodeA.depth + nodeB.depth - 2 * lcaNode.depth;

                if(pathLength <= c) {
                    fs.writer().write(nodeB.toString() + "\n");
                    continue;
                }

                int pathLengthFromAtoLCA = nodeA.depth - lcaNode.depth;

                if(pathLengthFromAtoLCA > c) {
                    fs.writer().write(nodeA.goUp(c).toString() + "\n");
                } else {
                    int bUp = pathLength - c;
                    fs.writer().write(nodeB.goUp(bUp).toString() + "\n");
                }
            }
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
    public static void fill(int[] arr, int num) {
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
