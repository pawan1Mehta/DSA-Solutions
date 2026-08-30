package Codeforces_Round_1076_Div3;

import java.io.*;
import java.util.*;

public class Product_Queries {

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int k = fs.nextInt();
        // int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            int[] a = fs.readIntArray(n);

            /*
                nums = [3 2 2 3 7 3 6 7]
                dp   = [1,1,1,1,1,1,1,1]
                dp   = [1,1,1,1,1,1,1,1]


                        ? ? ? ? ? ? ? ?
                        1 2 3 4 5 6 7 8
                              |

                i = 2   i <= 8  i++
                    j = 2   j <= 8   j++

                        i = 2 j = 4   1 <= 8

                    dp[j] = min(dp[j], dp[i] + dp[j/i]
                    MAX = min(MAX, 1 + 1)


                a * b = c
                2 * 4 = 8

                c/a = 4

                8/2 = 4
            * */

            int[] dp = new int[n + 1];
            fill(dp, Integer.MAX_VALUE);

            for(int num : a) {
                if(num > n) {
                    continue;
                }
                dp[num] = 1;
            }

            for(int i = 1; i <= n; i++) {
                if(dp[i] == Integer.MAX_VALUE) {
                    continue;
                }

                for(int j = i; j <= n; j += i) {
                    /*
                        a * b = c
                        a * ? = c
                        2 * ? = 8

                        c/a = 4
                     */
                    int m = j/i;
                    if(dp[m] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[i] + dp[m]);
                    }
                }
            }

            for(int i = 1; i <= n; i++) {
                fs.writer().write((dp[i] == Integer.MAX_VALUE ? -1 : dp[i]) + " ");
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
