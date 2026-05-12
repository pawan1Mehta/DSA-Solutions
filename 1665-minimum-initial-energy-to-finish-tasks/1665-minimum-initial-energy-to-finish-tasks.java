class Solution {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return Integer.compare(a[1] - a[0], b[1] - b[0]);
            }
        });

        int ans = 0;
        for(int[] task : tasks) {
            ans = Math.max(ans + task[0], task[1]);
        }
        
        return ans;
    }
}