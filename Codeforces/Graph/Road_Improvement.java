package Graph;

import java.io.*;
import java.util.*;


public class Road_Improvement {

    public static void solve(FastScanner fs, int n, int[][] roads) throws IOException {

        int[][] repairingDay = new int[n - 1][3];
        ArrayList<ArrayList<Integer>> cityAndRoadMap = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            cityAndRoadMap.add(new ArrayList<>());
        }

        int city1, city2;
        for(int i = 0; i < n - 1; i++) {
            city1 = roads[i][0];
            city2 = roads[i][1];

            repairingDay[i] = new int[]{city1, city2, Integer.MAX_VALUE};  // Integer.MAX_VALUE: repairing day is not decided

            cityAndRoadMap.get(city1).add(i);
            cityAndRoadMap.get(city2).add(i);
        }

        int maxRoadConnected = 0;
        int cityWithMaxRoadConnected = 0;

        for(int i = 0; i < n; i++) {
            if(cityAndRoadMap.get(i).size() > maxRoadConnected) {
                maxRoadConnected = cityAndRoadMap.get(i).size();
                cityWithMaxRoadConnected = i;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < maxRoadConnected; i++) {
            ans.add(new ArrayList<>());
        }

        Queue<int[]> bfs = new LinkedList<>();

        bfs.add(new int[]{cityWithMaxRoadConnected, 0}); // 0: dummy day

        while(!bfs.isEmpty()) {
            int[] curr = bfs.poll();

            int currCity = curr[0];
            int parentCityRepairingDay = curr[1];

            int day = 1;
            for(int road : cityAndRoadMap.get(currCity)) {
                int neighbor = repairingDay[road][0] == currCity ? repairingDay[road][1] :  repairingDay[road][0];

                if(repairingDay[road][2] != Integer.MAX_VALUE) { // It is already repaired
                    continue;
                }

                if(day == parentCityRepairingDay) {
                    day++;
                }

                repairingDay[road][2] = day;

                ans.get(day - 1).add(road);

                bfs.add(new int[]{neighbor, day});

                day++;
            }
        }

        fs.writer().write(maxRoadConnected + "\n");
        for(int i = 0; i < maxRoadConnected; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(ans.get(i).size());
            for (int r : ans.get(i)) {
                sb.append(" ").append(r + 1);
            }
            fs.writer().write(sb.toString() + "\n");
        }
    }

    public  static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        // int k = fs.nextInt();
        int k = 1;

        while(k-- > 0) {
            int n = fs.nextInt();

            int[][] roads = new int[n - 1][2];

            for(int i = 0; i < n - 1; i++) {
                int[] road = fs.readIntArray(2);
                road[0]--; road[1]--;
                roads[i] = road;
            }

            solve(fs, n, roads);
        }

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
