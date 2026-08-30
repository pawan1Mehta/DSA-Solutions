class Solution {

    private int binarySearch(long[] tasksSum, long k) {
        int n = tasksSum.length;

        int maxIndex = -1;
        int left = 0, right = n - 1;

        while(left <= right) {
            int mid = (left + right)/2;

            if(tasksSum[mid] <= k) {
                maxIndex = Math.max(maxIndex, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return maxIndex + 1;
    }

    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int m = shifts.length;

        long[] tasksSum = new long[n];
        tasksSum[0] = tasks[0];
        for(int i = 1; i < n; i++) {
            tasksSum[i] = tasksSum[i - 1] + tasks[i];
        }

        int[] res = new int[m];
        long prevTime = 0;
        for(int i = 0; i < m; i++) {
            int index = binarySearch(tasksSum, shifts[i] + prevTime);
            if(index == n) {
                res[i] = 0;
                prevTime = 0;
            } else {
                prevTime += shifts[i];
                res[i] = n - index;
            }
        }

        return res;
    }
}