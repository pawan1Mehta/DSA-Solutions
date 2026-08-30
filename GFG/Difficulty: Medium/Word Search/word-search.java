class Solution {
    private char visited = '#';
    
    private boolean isValidStep(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;    
    }
    
    private boolean isWordExistUtil(int i, int j, int k, String word, char[][] mat, int n, int m) {
        if(k == word.length()) {
            return true;
        }
        
        if(!isValidStep(i, j, n, m) || word.charAt(k) != mat[i][j]) {
            return false;
        }
        
        char prevChar = mat[i][j];
        mat[i][j] = visited;
        
        boolean left = isWordExistUtil(i, j - 1, k + 1, word, mat, n, m);
        boolean right = isWordExistUtil(i, j + 1, k + 1, word, mat, n, m);
        boolean up = isWordExistUtil(i - 1, j, k + 1, word, mat, n, m);
        boolean down = isWordExistUtil(i + 1, j, k + 1, word, mat, n, m);
        
        mat[i][j] = prevChar;
        
        return left || right || up || down;
    }
    
    public boolean isWordExist(char[][] mat, String word) {
        int n = mat.length;
        int m = mat[0].length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == word.charAt(0)) {
                    if(isWordExistUtil(i, j, 0, word, mat, n, m)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}