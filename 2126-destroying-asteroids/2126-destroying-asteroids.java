class Solution {

    private int binarySearch(ArrayList<Integer> list, long mass) {
        int n = list.size();

        int low = 0, high = n - 1;
        int res = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(list.get(mid) <= mass) {
                res = Math.max(res, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    public boolean asteroidsDestroyed(int _mass, int[] asteroids) {
        int n = asteroids.length;

        ArrayList<Integer> list = new ArrayList<>();
        for(int asterod : asteroids) {
            list.add(asterod);
        }

        Collections.sort(list);

        long mass = _mass;

        for(int i = 0; i < n; i++) {
            int targetIndex = binarySearch(list, mass);
            if(targetIndex == -1) {
                return false;
            }
            mass += list.get(targetIndex);
            list.remove(targetIndex);
        }

        return true;
    }
}