class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;

        int[] lightsStatus = new int[n];

        for(int i = 0; i < n; i++) {
            int v = lights[i];
            if(v != 0) {
                int left = Math.max(0, i - v);
                lightsStatus[left] += 1;

                if((i + v + 1) <= n-1) {
                    lightsStatus[(i + v + 1)] -= 1;
                }
            }
        }

        for(int i = 1; i < n; i++) {
            lightsStatus[i] += lightsStatus[i - 1];
        }

        int count = 0;
        for(int i = 0; i < n; ) {
            int v = lightsStatus[i];
            if(v == 0) {
                i += 3;
                count++;
            } else {
                i++;
            }
        }

        return count;
    }
}