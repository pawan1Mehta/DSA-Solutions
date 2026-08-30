import java.util.Arrays;

/**
 * Fixed version of the original recursive memo solution.
 * Same skeleton: maxTotalUtil + opt1/opt2 + memo — with three targeted fixes.
 */
class Solution {
    private long[][] memo; // FIX #1: was long[] memo — need 2 states, not just index i
    private int[] nums;
    private String s;

    /**
     * FIX #2: process RIGHT -> LEFT (i goes down), because every move goes to i - 1.
     * Your left-to-right loop already decided index i-1 before you tried to move into it.
     *
     * FIX #3: replace StringBuilder with `incoming`.
     * - incoming = 0  -> index i is free
     * - incoming = 1  -> index i is already taken by the token that moved from i + 1
     * StringBuilder changed every branch, but memo[i] ignored that layout -> wrong reuse.
     */
    private long maxTotalUtil(int i, int incoming) {
        if (i < 0) { // was: if (i == nums.length) with left-to-right
            return 0;
        }

        if (memo[i][incoming] != -1) {
            return memo[i][incoming];
        }

        long best = 0;

        if (incoming == 1) {
            // Index i is covered by token from i + 1 — count it here (you never counted this case)
            best = nums[i] + maxTotalUtil(i - 1, 0);

            if (s.charAt(i) == '1' && i > 0) {
                // Token at i cannot stay (conflict). It must move left.
                // Same shape as your opt1, but no StringBuilder swap and no nums[i - 1] here.
                long opt1 = nums[i] + maxTotalUtil(i - 1, 1);
                best = Math.max(best, opt1);
            }
        } else if (s.charAt(i) == '0') {
            // No token starts here; keep walking left
            best = maxTotalUtil(i - 1, 0);
        } else {
            // s.charAt(i) == '1' — your opt1 / opt2, corrected for right-to-left + incoming

            // opt2: stay at i (same idea as yours: take nums[i] and continue)
            long opt2 = nums[i] + maxTotalUtil(i - 1, 0);
            best = opt2;

            // opt1: move to i - 1
            // BUG in your code: you checked s.charAt(i - 1) == '0' and added nums[i - 1] immediately.
            // Correct: mark i - 1 as incoming; nums[i - 1] is counted when that cell is processed.
            if (i > 0) {
                long opt1 = maxTotalUtil(i - 1, 1);
                best = Math.max(best, opt1);
            }
        }

        return memo[i][incoming] = best;
    }

    public long maxTotal(int[] nums, String s) {
        this.nums = nums;
        this.s = s;
        int n = nums.length;

        if (n == 1) {
            return s.charAt(0) == '1' ? nums[0] : 0;
        }

        memo = new long[n][2]; // was: new long[n]
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        return maxTotalUtil(n - 1, 0); // was: maxTotalUtil(0, nums, new StringBuilder(s))
    }

    // quick sanity check for the failing case
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxTotal(new int[] {2, 2, 10, 4}, "0111")); // 16
        System.out.println(sol.maxTotal(new int[] {9, 2, 6, 1}, "0101")); // 15
        System.out.println(sol.maxTotal(new int[] {5, 1, 4}, "001"));       // 4
        System.out.println(sol.maxTotal(new int[] {9, 3, 5}, "011"));       // 14
    }
}
