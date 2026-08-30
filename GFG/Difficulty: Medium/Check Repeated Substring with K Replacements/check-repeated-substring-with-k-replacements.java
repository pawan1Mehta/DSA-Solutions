class Solution {
    public boolean kSubstr(String s, int k) {
        int n = s.length();
        
        Map<String, Integer> mp = new HashMap<>();
        
        for(int i = k; i <= n; i += k) {
            String str = s.substring(i - k, i);
            mp.put(str, mp.getOrDefault(str, 0) + 1);
        }
        
        if(mp.size() > 2) {
            return false;
        }
        if(mp.size() == 1) {
            return true;
        }
        
        List<Integer> values = new ArrayList<>(mp.values());
                
        int a = values.get(0);
        int b = values.get(1);
        
        int num = n/k;
        
        if((a == (num - 1) && b == 1) || (a == 1 && b == (num - 1))) {
            return true;
        }
        
        return false;
    }
}