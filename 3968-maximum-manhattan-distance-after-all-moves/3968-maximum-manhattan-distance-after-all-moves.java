class Solution {
    public int maxDistance(String moves) {
        int underscoreCount = 0;
        int maxMahattanDist = 0;

        int destX = 0, destY = 0;
        int currX = 0, currY = 0;

        for(char ch : moves.toCharArray()) {
            if(ch == '_') {
                underscoreCount++;
            } else {
                switch(ch) {
                    case 'L':
                        currY--;
                        break;
                    case 'R':
                        currY++;
                        break;
                    case 'D':
                        currX++;
                        break;
                    case 'U':
                        currX--;
                        break;
                    default:
                        break;
                }
            }
        }

        return (Math.abs(currX - destX) + Math.abs(currY - destY)) + underscoreCount;
    }
}