class Solution {
    public int numSteps(String s) {
        StringBuilder bStr = new StringBuilder();
        bStr.append('0');
        bStr.append(s);

        int n = bStr.length();

        int l = 0, r = n - 1;

        while(l < n && bStr.charAt(l) == '0') {
            l++;
        }

        int numSteps = 0;

        while(l < r) {
            if(bStr.charAt(r) == '0') {
                r--;
            } else {
                addOne(bStr, r);
                if(l > 0 && bStr.charAt(l - 1) == '1') {
                    l--;
                }
            }

            numSteps++;
        }

        return numSteps;
    }

    private void addOne(StringBuilder bStr, int index) {
        int remainder = 1;
        while(index >= 0) {
            int num = (bStr.charAt(index) == '0') ? 0 : 1;
            int sum = num + remainder;
            
            if(sum > 1) {
                bStr.setCharAt(index, '0');
                remainder = 1;
            } else {
                bStr.setCharAt(index, '1');
                remainder = 0;
                break;
            }

            index--;
        }
    }
}