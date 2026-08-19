class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> mp = new HashMap<>();

        for(int[] seat : reservedSeats) {
            if(seat[1] >= 2 && seat[1] <= 9) {
                int bitmask = mp.getOrDefault(seat[0], 0);
                int newBitmask = bitmask | (1 << seat[1] - 2);
                mp.put(seat[0], newBitmask);
            }
        }

        int count = (n - mp.size()) * 2;

        int leftFree = 0b11110000;
        int midFree = 0b11000011;
        int rightFree = 0b00001111;

        for(int seatBitmask : mp.values()) {
            if((seatBitmask | leftFree) == leftFree ||
               (seatBitmask | midFree) == midFree ||
               (seatBitmask | rightFree) == rightFree) {
                count++;
            }
        }

        return count;
    }
}