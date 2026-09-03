class Solution {
    private int NORTH = 1;
    private int EAST = 2;
    private int SOUTH = 3;
    private int WEST = 4;

    private int[] rotate90DegreesRight(int x, int y, int dir) {
        if(dir == NORTH) {
            dir = EAST;
        } else if(dir == EAST) {
            dir = SOUTH;
        } else if(dir == SOUTH) {
            dir = WEST;
        } else {
            dir = NORTH;
        }
        return new int[]{x, y, dir};
    }

    private int[] rotate90DegreesLeft(int x, int y, int dir) {
        if(dir == NORTH) {
            dir = WEST;
        } else if(dir == WEST) {
            dir = SOUTH;
        } else if(dir == SOUTH) {
            dir = EAST;
        } else {
            dir = NORTH;
        }
        return new int[]{x, y, dir};
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstaclesSt = new HashSet<>();
        
        for(int[] obstacle : obstacles) {
            obstaclesSt.add(obstacle[0] + "-" + obstacle[1]);
        }

        Queue<int[]> cords = new LinkedList<>();

        cords.add(new int[]{0, 0, NORTH});

        int res = 0;

        int x, y, dir;
        for(int cmd : commands) {
            int n = cords.size();
            while(n-- > 0) {
                int[] cord = cords.poll();
                x = cord[0];
                y = cord[1];
                dir = cord[2];

                if(cmd == -1) {
                    cords.add(rotate90DegreesRight(x, y, dir));
                } else if(cmd == -2) {
                    cords.add(rotate90DegreesLeft(x, y, dir));
                } else {
                    
                    if(dir == NORTH) {
                        for(int num = 1; num <= cmd; num++) {
                            y++;
                            if(obstaclesSt.contains(x + "-" + y)) {
                                y--;
                                break;
                            }
                        }
                    } else if(dir == EAST) {
                        for(int num = 1; num <= cmd; num++) {
                            x++;
                            if(obstaclesSt.contains(x + "-" + y)) {
                                x--;
                                break;
                            }
                        }
                    } else if(dir == SOUTH) {
                        for(int num = 1; num <= cmd; num++) {
                            y--;
                            if(obstaclesSt.contains(x + "-" + y)) {
                                y++;
                                break;
                            }
                        }
                    } else {
                        for(int num = 1; num <= cmd; num++) {
                            x--;
                            if(obstaclesSt.contains(x + "-" + y)) {
                                x++;
                                break;
                            }
                        }
                    }

                    cords.add(new int[] {x, y, dir});

                    res = Math.max(res, (int)Math.pow(x, 2) + (int)Math.pow(y, 2));
                }
            }
        }

        return res;
    }
}