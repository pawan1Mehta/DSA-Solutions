class Solution {
    private boolean prefers(int[][] women, int w, int m, int wp) {
        int n = women[w].length;
        
        for(int i = 0; i < n; i++) {
            if(women[w][i] == m) {
                return true;
            }
            if(women[w][i] == wp) {
                return false;
            }
        }
        
        return false;
    }
    
    public int[] stableMarriage(int[][] men, int[][] women) {
        int n = men.length;
        
        int[] mPartner = new int[n];
        int[] wPartner = new int[n];
        
        Arrays.fill(mPartner, -1);
        Arrays.fill(wPartner, -1);
        
        int[] proposalIndex = new int[n];
        boolean[] freeMen = new boolean[n];
        
        Arrays.fill(freeMen, true);
        
        int freeMenCount = n;
        
        while(freeMenCount > 0) {
            int currFreeMen = 0;
            for(currFreeMen = 0; currFreeMen < n; currFreeMen++) {
                if(freeMen[currFreeMen]) {
                    break;
                }
            }
            
            int w = men[currFreeMen][proposalIndex[currFreeMen]];
            proposalIndex[currFreeMen]++;
            
            if(wPartner[w] == -1) {
                wPartner[w] = currFreeMen;
                mPartner[currFreeMen] = w;
                
                freeMen[currFreeMen] = false;
                freeMenCount--;
            } else {
                int wp = wPartner[w];
                if(prefers(women, w, currFreeMen, wp)) {
                    
                    wPartner[w] = currFreeMen;
                    mPartner[currFreeMen] = w;
                    
                    freeMen[currFreeMen] = false;
                    freeMen[wp] = true;
                }
            }
        }
        
        return mPartner;
    }
}