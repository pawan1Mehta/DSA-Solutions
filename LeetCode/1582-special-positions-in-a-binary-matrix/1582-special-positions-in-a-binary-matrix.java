class Solution {
    public int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 1) {
                    row.putIfAbsent(i, new HashSet<>());
                    row.get(i).add(j);
                }
            }
        }

        for(int j = 0; j < m; j++) {
            for(int i = 0; i < n; i++) {
                if(mat[i][j] == 1) {
                    col.putIfAbsent(j, new HashSet<>());
                    col.get(j).add(i);
                }
            }
        }

        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 1) {
                    if(row.containsKey(i) && row.get(i).size() == 1
                        && col.containsKey(j) && col.get(j).size() == 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}