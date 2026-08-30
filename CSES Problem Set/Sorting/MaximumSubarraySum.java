package CSESProblemSet.Sorting;

import java.io.*;
import java.util.*;

public class MaximumSubarraySum {

    private static long maximumSubarraySum(int[] arr) {
        long max_so_far = Long.MIN_VALUE;
        long curr_max_sum = 0;

        for(int num : arr) {
            curr_max_sum += num;

            if(curr_max_sum < num) {
                curr_max_sum = num;
            }

            if(max_so_far < curr_max_sum) {
                max_so_far = curr_max_sum;
            }
        }

        return max_so_far;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int t = fs.nextInt();
        int t = 1;

        while (t-- > 0) {
            int n = fs.nextInt();
            int[] arr = fs.readIntArray(n);

            long sum = maximumSubarraySum(arr);

            fs.writer().write(sum + "");

            fs.writer().write("\n");
        }

        fs.close();
    }

    // =========================================================  Utils =========================================================
    public static long[][][][] dp;

    public static int MOD = 1_000_000_007;

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void fill(int[][] arr, int num) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }

    public static void fill(long[][] arr, long num) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }

    public static void fill(long[][][] arr, long num) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }

    public static void fill(long[][][][] arr, long num) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            fill(arr[i], num);
        }
    }

    public static void fill(int[] arr, int num) {
        Arrays.fill(arr, num);
    }

    public static void fill(long[] arr, long num) {
        Arrays.fill(arr, num);
    }

    public static long sum(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int[] getDigits(long n) {
        ArrayList<Integer> list = new ArrayList<>();

        while(n > 0) {
            list.add((int)(n % 10));
            n = n/10;
        }

        int[] digits = new int[list.size()];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = list.get(list.size() - i - 1);
        }

        return digits;
    }

    // ======================================== Math ======================================================
    public static long[] p;

    public static void power(int num, int n, long mod) {
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = (num * p[i - 1]) % mod;
        }
    }

    public static void power(int num, int n) {
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
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
            if ((b & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
    }

    // ======================================== String ======================================================
    public static int[] zArray(String str) {
        int n = str.length();

        int[] zArray = new int[n];

        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                int k = i - l;
                zArray[i] = Math.min(zArray[k], r - i + 1);
            }

            while ((i + zArray[i]) < n && str.charAt(zArray[i]) == str.charAt(i + zArray[i])) {
                zArray[i]++;
            }

            if ((i + zArray[i] - 1) > r) {
                l = i;
                r = i + zArray[i] - 1;
            }
        }

        return zArray;
    }

    public static int[] zArray(int[] nums) {
        int n = nums.length;

        int[] zArray = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i < r) {
                // a a b a $ a a b a k o
                //           4 |
                int k = i - l;
                zArray[i] = Math.min(zArray[k], r - i + 1);
            }

            while ((i + zArray[i]) < n && nums[zArray[i]] == nums[i + zArray[i]]) {
                zArray[i]++;
            }

            if ((i + zArray[i] - 1) > r) {
                l = i;
                r = i + zArray[i] - 1;
            }
        }

        return zArray;
    }

    public static int[] LPS(String str) {
        int n = str.length();

        int[] lps = new int[n];

        lps[0] = 0;

        int idx = 1;
        int lpsLen = 0;

        while (idx < n) {
            if (str.charAt(idx) == str.charAt(lpsLen)) {
                lpsLen++;
                lps[idx] = lpsLen;
                idx++;
            } else {
                if (lpsLen == 0) {
                    lps[idx] = 0;
                    idx++;
                } else {
                    lpsLen = lps[lpsLen - 1];
                }
            }
        }

        return lps;
    }

    // ======================================== Graph ======================================================
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    private static boolean bfsColoring(int srcNode, ArrayList<ArrayList<Integer>> adjList, int[] color) {
        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{srcNode, 1});
        color[srcNode] = 1;

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();

            int currNode = curr[0];
            int currCol = curr[1];

            for (int adjNode : adjList.get(currNode)) {
                if (currCol == color[adjNode]) {
                    return false;
                }

                if (color[adjNode] == -1) {
                    color[adjNode] = currCol == 1 ? 0 : 1;
                    bfs.add(new int[]{adjNode, color[adjNode]});
                }
            }
        }

        return true;
    }

    private static boolean graphColoring(int n, int[] color, ArrayList<ArrayList<Integer>> adjList) {
        Arrays.fill(color, -1);

        for (int node = 0; node < n; node++) {
            if (color[node] == -1) {
                if (!bfsColoring(node, adjList, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    // ======================================== DEBUG ======================================================
    public static void printArr(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        for (int i = 0; i < n; i++) {
            printArr(arr[i]);
        }
    }

    public static void printArr(int[] arr) {
        int n = arr.length;
        System.out.print("arr: [");
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println(" ] \n");
    }

    public static void printList(ArrayList<Integer> list) {
        int n = list.size();
        System.out.print("list: [");
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.print(list.get(i));
            } else {
                System.out.print(list.get(i) + ", ");
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

        String nextString() {
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