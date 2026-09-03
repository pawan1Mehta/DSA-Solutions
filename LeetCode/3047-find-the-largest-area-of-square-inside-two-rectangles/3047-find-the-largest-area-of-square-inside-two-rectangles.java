class Solution {
    private boolean isRectanglesIntersecting(int[] rect1B, int[] rect1T, int[] rect2B, int[] rect2T) {
        int rect1x1 = rect1B[0];
        int rect1y1 = rect1B[1];
        int rect1x2 = rect1T[0];
        int rect1y2 = rect1T[1];

        int rect2x1 = rect2B[0];
        int rect2y1 = rect2B[1];
        int rect2x2 = rect2T[0];
        int rect2y2 = rect2T[1];

        if (rect1x1 <= rect2x2 && rect1x2 >= rect2x1 &&
            rect1y1 <= rect2y2 && rect1y2 >= rect2y1) {
            return true;
        }
        
        return false;
    }

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        
        long maxSquare = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isRectanglesIntersecting(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j])) {
                    int x1 = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                    int y1 = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                    int x2 = Math.min(topRight[i][0], topRight[j][0]);
                    int y2 = Math.min(topRight[i][1], topRight[j][1]);

                    int widht = Math.max(x2 - x1, 0);
                    int height = Math.max(y2 - y1, 0);
                    int side = Math.min(widht, height);
        
                    long area = (long) side * side;

                    maxSquare = Math.max(maxSquare, area);
                }
            }
        }

        return maxSquare;
    }
}