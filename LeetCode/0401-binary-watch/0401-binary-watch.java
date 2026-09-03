class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();

        for(int hr = 0; hr <= 11; hr++) {
            for(int mn = 0; mn <= 59; mn++) {
                if(Integer.bitCount(hr) + Integer.bitCount(mn) == turnedOn) {
                    list.add(hr + ":" + (mn <= 9 ? "0" + mn : mn));
                }
            }
        }

        return list;
    }
}