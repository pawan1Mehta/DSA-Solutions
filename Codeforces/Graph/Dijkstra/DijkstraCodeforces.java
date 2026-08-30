package Graph.Dijkstra;

import java.io.*;
import java.util.*;

public class DijkstraCodeforces {

    private static int[] shortestPath(ArrayList<ArrayList<int[]>> adjList, int n) {
        int srcNode = 1;

        int[] lastUpdateMe = new int[n + 1];
        int[] dist = new int[n + 1];
        fill(dist, Integer.MAX_VALUE);
        fill(lastUpdateMe, -1);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] edge1, int[] edge2) {
                return Integer.compare(edge1[1], edge2[1]);
            }
        });

        lastUpdateMe[srcNode] = srcNode;
        dist[srcNode] = 0;
        minHeap.add(new int[]{srcNode, 0});

        int u, v, wt;
        while (!minHeap.isEmpty()) {
            int[] currNode = minHeap.poll();
            u = currNode[0];

            if(currNode[1] > dist[u]) {
                continue;
            }

            for(int[] adjNode : adjList.get(u)) {
                v = adjNode[0]; wt = adjNode[1];
                if(dist[v] > (dist[u] + wt)) {
                    lastUpdateMe[v] = u;
                    dist[v] = dist[u] + wt;
                    minHeap.add(new int[]{v, dist[v]});
                }
            }
        }

        if(dist[n] == Integer.MAX_VALUE) {
            return new int[]{-1};
        }

        ArrayList<Integer> res = new ArrayList<>();

        for(int node = n; node != 1; node = lastUpdateMe[node]) {
            res.add(node);
        }
        res.add(1);

        Collections.reverse(res);

        int[] path = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            path[i] = res.get(i);
        }

        return path;
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();

            for(int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }

            int u, v, wt;
            for(int i = 0; i < m; i++) {
                u = fs.nextInt();
                v = fs.nextInt();
                wt = fs.nextInt();

                adjList.get(u).add(new int[]{v, wt});
                adjList.get(v).add(new int[]{u, wt});
            }

            int[] path = shortestPath(adjList, n);
            if(path[0] == -1) {
                fs.writer().write("-1 \n");
            } else {
                for(int node : path) {
                    fs.writer().write(node + " ");
                }
                fs.writer().write("\n");
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
