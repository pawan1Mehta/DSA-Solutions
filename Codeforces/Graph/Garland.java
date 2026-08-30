package Graph;

import java.io.*;
import java.util.*;

public class Garland {

    /*
    Using DFS TLE(Time limit exceeded)

        public static int[] result;

        public static int dfs(int lamp, ArrayList<ArrayList<Integer>> lamps, int[] temperature,  int holdingLamp, int reqTemp) {
            int totalTemp = temperature[lamp];

            for(int lmp : lamps.get(lamp)) {
                totalTemp += dfs(lmp, lamps, temperature, holdingLamp, reqTemp);
            }

            if(totalTemp == reqTemp && lamp != holdingLamp) {
                if(result[0] == -1) {
                    result[0] = lamp;
                } else if(result[1] == -1){
                    result[1] = lamp;
                }

                return 0;
            } else {
                return totalTemp;
            }
        }
    */

    public static void solve(FastScanner fs, int n, ArrayList<ArrayList<Integer>> lamps, int[] degree, int[] temperature,  int holdingLamp, int reqTemp) throws IOException {
        int[] result = new int[]{-1, -1};

        Queue<int[]> bfs = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0 && i != holdingLamp) {
                bfs.add(new int[]{i, temperature[i]});
            }
        }

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int lamp = curr[0];
            int val = curr[1];

            if(curr[1] == reqTemp) {
                if(lamp != holdingLamp && lamp != 0) {
                    if(result[0] == -1) {
                        result[0] = lamp;
                    } else if(result[1] == -1) {
                        result[1] = lamp;
                    } else {
                        break;
                    }
                }
                val = 0;
            }
            for(int lmp : lamps.get(lamp)) {
                temperature[lmp] += val;
                degree[lmp]--;
                if(degree[lmp] == 0) {
                    bfs.add(new int[]{lmp, temperature[lmp]});
                }
            }
        }

        if(result[1] == -1) {
            fs.writer().write("-1 \n");
        } else {
            fs.writer().write(result[0] + " " + result[1] + "\n");
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        // while(k-- > 0) {
        int n = fs.nextInt();

        ArrayList<ArrayList<Integer>> lamps = new ArrayList<>();
        int[] temperature = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            lamps.add(new ArrayList<>());
            temperature[i] = 0;
        }

        int totalTemp = 0;

        int holdingLamp = -1;

        int[] degree = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            int[] lamp = fs.readIntArray(2);
            int lampNo = lamp[0];
            int lampTemp = lamp[1];

            if(lampNo == 0) {
                holdingLamp = i;
            }

            if (lampNo != 0) {
                lamps.get(i).add(lampNo);
                degree[lampNo]++;
            }

            lamps.get(lampNo).add(i);
            temperature[i] = lampTemp;

            totalTemp += lampTemp;
        }

        if(totalTemp%3 != 0) {
            fs.writer().write("-1 \n");
        } else {
            solve(fs, n, lamps, degree,  temperature, holdingLamp, totalTemp/3);
        }
        // }

        fs.close();
    }

    public  static int MOD = 1_000_000_007;

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
