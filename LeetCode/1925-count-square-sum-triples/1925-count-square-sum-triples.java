class Solution {
    public int countTriples(int n) {
        int count = 0;

        for(int num = n; num >= 2; num--) {
            for(int i = 1; i < num; i++) {
                for(int j = 1; j < num; j++) {
                    if((i * i + j * j) == num * num) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}