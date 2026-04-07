class Robot {
    private final int NORTH = 1;
    private final int EAST = 2;
    private final int SOUTH = 3;
    private final int WEST = 4;
    
    int width, height;
    int currX, currY;
    int currDir;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;

        this.currX = 0;
        this.currY = 0;   
        this.currDir = EAST; 
    }
    
    public void step(int num) {
        int x = currX;
        int y = currY;

        for(int k = 1; k <= num; k++) {
             if(currDir == EAST) {
                x++;
                if(x >= width) {
                    x--;
                    y++;
                    currDir = NORTH;
                }
            } else if(currDir == NORTH) {
                y++;
                if(y >= height) {
                    y--;
                    x--;
                    currDir = WEST;
                }
            } else if(currDir == WEST) {
                x--;
                if(x < 0) {
                    x++;
                    y--;
                    currDir = SOUTH;
                }
            } else {
                y--;
                if(y < 0) {
                    y++;
                    x++;
                    currDir = EAST;
                }
            }
        }

        currX = x;
        currY = y;
    }
    
    public int[] getPos() {
        return new int[]{currX, currY};
    }
    
    public String getDir() {
        if(currDir == NORTH) {
            return "North";
        } else if(currDir == EAST) {
            return "East";
        } else if(currDir == SOUTH) {
            return "South";
        } else {
            return "West";
        }
    }
}