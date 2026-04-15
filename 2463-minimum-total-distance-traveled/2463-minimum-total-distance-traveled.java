class Solution {

    long[][] memo;

    private long minimumTotalDistanceUtil(int robotIdx, int factoryIdx, List<Integer> robot, List<Integer> factoryPositions) {
        if(robotIdx == robot.size()) {
            return 0;
        }

        if(factoryIdx == factoryPositions.size()) {
            return (long)1e12;
        }

        if(memo[robotIdx][factoryIdx] != -1) {
            return memo[robotIdx][factoryIdx];
        }

        long opt1 = Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx))
                      + minimumTotalDistanceUtil(
                            robotIdx + 1, 
                            factoryIdx + 1,
                            robot,
                            factoryPositions
                        );
        
        long opt2 = minimumTotalDistanceUtil(
                        robotIdx, 
                        factoryIdx + 1,
                        robot,
                        factoryPositions
                    );

        return memo[robotIdx][factoryIdx] = Math.min(opt1, opt2);
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);

        Arrays.sort(factory, new Comparator<int[]>(){
            public int compare(int[] f1, int[] f2) {
                return Integer.compare(f1[0], f2[0]);
            }
        });

        List<Integer> factoryPositions = new ArrayList<>();

        for(int[] f : factory) {
            for(int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        int n = robot.size();
        int m = factoryPositions.size();

        memo = new long[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return minimumTotalDistanceUtil(0, 0, robot, factoryPositions);
    }
}