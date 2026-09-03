class Solution {
    private int numGrayCodeUtil(int n) {
        return n ^ (n >> 1);
    }

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();

        int sequenceLength = 1 << n;
        for(int i = 0; i < sequenceLength; i++) {
            res.add(numGrayCodeUtil(i));
        }

        return res;
    }
}