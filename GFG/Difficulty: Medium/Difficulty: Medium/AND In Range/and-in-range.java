class Solution {
    public int andInRange(int l, int r){
    
        int shiftCount = 0;
        
        while (l != r && l > 0) {
            shiftCount++;
            l = l >> 1;
            r = r >> 1;
        }
        
        return (l << shiftCount);
    }
}

/*


    00000001
    
    00000010
    00000011
    00000100 4
    00000101
    00000110
    
    
    1000 8
    1001 9
    1010 10
    1011 11
    1100 12
    1101
    
    





*/