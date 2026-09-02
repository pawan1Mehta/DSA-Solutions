class Solution {
    public int scoreDifference(int[] nums) {
        int n = nums.length;
        
        Set<Integer> sixthIndex = new HashSet<>();
        for(int i = 5; i < n; i += 6) {
            sixthIndex.add(i);
        }

        int player1 = 0, player2 = 0;
        boolean isPlayer1Turn = true;

        for(int i = 0; i < n; i++) {
            if(nums[i]%2 != 0) {
                isPlayer1Turn = !isPlayer1Turn;
            }
            if(sixthIndex.contains(i)) {
                isPlayer1Turn = !isPlayer1Turn;
            }

            if(isPlayer1Turn) {
                player1 += nums[i];
            } else {
                player2 += nums[i];
            }
        }

        return player1 - player2;
    }
}