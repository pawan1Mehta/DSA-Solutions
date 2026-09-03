class Solution {
    public int[] simulationResult(int[] windows, int[] queries) {
        int n = windows.length;
        int m = queries.length;

        ArrayList<Integer> resWindows = new ArrayList<>();
        Set<Integer> st = new HashSet<>();

        for(int i = m - 1; i >= 0; i--) {
            if(!st.contains(queries[i])) {
                resWindows.add(queries[i]);
                st.add(queries[i]);
            }
        }

        for(int i = 0; i < n; i++) {
            if(!st.contains(windows[i])) {
                resWindows.add(windows[i]);
            }
        }

        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            res[i] = resWindows.get(i);
        }

        return res;
    }
}