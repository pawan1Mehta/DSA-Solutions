class Solution {
    public int maxScore(String s, char[][] jumps) {
        int n = s.length();

        ArrayList<char[]> jumpsList = new ArrayList<>();
        for (int i = 0; i < jumps.length; i++) {
            jumpsList.add(new char[]{jumps[i][0], jumps[i][1]});
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            jumpsList.add(new char[]{ch, ch});
        }

        int[][] nxtInd = new int[n][26];
        for (int i = 0; i < n; i++)
            Arrays.fill(nxtInd[i], -1);

        int[] lastInd = new int[26];
        Arrays.fill(lastInd, -1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++)
                nxtInd[i][j] = lastInd[j];

            lastInd[s.charAt(i) - 'a'] = i;
        }

        ArrayList<ArrayList<Integer>> child = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            child.add(new ArrayList<>());

        for (int i = 0; i < jumpsList.size(); i++) {
            char u = jumpsList.get(i)[0];
            char v = jumpsList.get(i)[1];
            child.get(u - 'a').add((int) v);
        }

        int[] preScore = new int[n + 1];
       
        for (int i = 0; i < s.length(); i++)
            preScore[i + 1] = preScore[i] + s.charAt(i);

        int[] dp = new int[n];

        for (int ind = n - 2; ind >= 0; ind--) {
            int score = 0;
            
            for (int it : child.get(s.charAt(ind) - 'a')) {
                int jmpInd = nxtInd[ind][it - 'a'];
                if (jmpInd == -1)
                    continue;
                    
                if (it == s.charAt(ind)) {
                    int temp = preScore[jmpInd] - preScore[ind + 1] + dp[jmpInd];
                    score = Math.max(score, temp);
                } else {
                    int temp = preScore[jmpInd] - preScore[ind] + dp[jmpInd];
                    score = Math.max(score, temp);
                }
            }
            
            dp[ind] = score;
        }
        
        return dp[0];
    }
}