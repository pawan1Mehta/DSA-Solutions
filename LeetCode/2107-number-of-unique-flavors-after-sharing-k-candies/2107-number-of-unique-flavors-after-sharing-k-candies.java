class Solution {
    public int shareCandies(int[] candies, int k) {
        int n = candies.length;

        Map<Integer, Integer> candiesFlavors = new HashMap<>();
        for(int num : candies) {
            candiesFlavors.put(num, candiesFlavors.getOrDefault(num, 0) + 1);
        }

        if(k == 0) {
            return candiesFlavors.size();
        }
        
        int maxCandieFalvor = 0;
        int i = 0, j = 0;

        while(j < n) {
            candiesFlavors.put(candies[j], candiesFlavors.get(candies[j]) - 1);
            if(candiesFlavors.get(candies[j]) == 0) {
                candiesFlavors.remove(candies[j]);
            }

            if((j - i + 1) > k) {
                candiesFlavors.put(candies[i], candiesFlavors.getOrDefault(candies[i], 0) + 1);
                i++;
            }

            if((j - i + 1) == k) {
                maxCandieFalvor = Math.max(maxCandieFalvor, candiesFlavors.size());
            }

            j++;
        }

        return maxCandieFalvor;
    }
}