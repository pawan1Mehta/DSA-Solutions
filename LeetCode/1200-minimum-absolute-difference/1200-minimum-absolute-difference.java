class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);

        int minAbsDiff = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            minAbsDiff = Math.min(minAbsDiff, Math.abs(arr[i] - arr[i - 1]));
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i] && ((arr[i] - arr[i - 1]) == minAbsDiff)) {
                res.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return res;
    }
}