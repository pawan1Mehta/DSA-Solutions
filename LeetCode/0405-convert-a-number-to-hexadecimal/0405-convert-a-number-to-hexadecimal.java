class Solution {
    public String toHex(int num) {
        if(num == 0) {
            return "0";
        }

        char[] hexaCodeMp = new char[]{
            '0', '1', '2', '3', '4', '5', '6','7','8','9',
            'a','b','c','d','e','f'
        };

        StringBuilder numStr = new StringBuilder();

        while(num != 0) {
            int hexBits = num & 15;
            numStr.append(hexaCodeMp[hexBits]);
            num = (num >>> 4);
        }

        return numStr.reverse().toString();
    }
}