class Solution {
    public ArrayList<Integer> constructList(int[][] queries) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);
        
        int allXorNum = 0;
        
        for(int[] query : queries) {
            if(query[0] == 0) {
                int x = query[1];
                res.add(x ^ allXorNum);
            } else {
                allXorNum = allXorNum ^ query[1];
            }
        }
    
        for(int i = 0; i < res.size(); i++) {
            res.set(i, res.get(i) ^ allXorNum);
        }
        
        Collections.sort(res);
        
        return res;
    }
}