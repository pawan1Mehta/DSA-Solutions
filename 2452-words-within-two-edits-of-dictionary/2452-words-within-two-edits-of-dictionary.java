class Solution {
    
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n = queries.length;
        int m = dictionary.length;

        Set<String> dict = new HashSet<>();

        for(String word : dictionary) {
            dict.add(word);
        }

        List<String> res = new ArrayList<>();
        
        for(String query : queries) {
            for(String dictWord : dictionary) {
                int l = query.length();

                int countDiff = 0;

                for(int i = 0; i < l; i++) {
                    if(query.charAt(i) != dictWord.charAt(i)) {
                        countDiff++;
                    }
                }

                if(countDiff <= 2) {
                    res.add(query);
                    break;
                }
            }
        }

        return res;
    }
}