class Solution {
    private List<Integer> res;

    private void generateSequentialDigits(int num, int low, int high) {
        if(num > high) {
            return;
        }

        if(num >= low) {
            res.add(num);
        }

        int lastDigit = num%10;

        if(lastDigit == 9) {
            return;
        }

        generateSequentialDigits(num * 10 + (lastDigit + 1), low, high);
    }

    public List<Integer> sequentialDigits(int low, int high) {
        res = new ArrayList<>();

        for(int i = 1; i <= 8; i++) {
            generateSequentialDigits(i, low, high);
        }

        Collections.sort(res);

        return res;
    }
}