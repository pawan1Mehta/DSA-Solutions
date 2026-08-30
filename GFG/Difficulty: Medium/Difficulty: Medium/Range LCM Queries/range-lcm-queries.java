
class LCMSegmentTree {
    int n;
    long[] tree;
    
    public LCMSegmentTree(int n, int[] nums) {
        this.n = n;
        this.tree = new long[4 * n];
        
        buildTree(1, 0, n - 1, nums);
    }
    
    private void buildTree(int v, int tl, int tr, int[] nums) {
        if(tl == tr) {
            tree[v] = nums[tl];
            return;
        }
        
        int tmid = (tl + tr)/2;
        
        buildTree(2 * v, tl, tmid, nums);
        buildTree(2 * v + 1, tmid + 1, tr, nums);
        
        tree[v] = lcm(tree[2 * v], tree[2 * v + 1]);
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a%b);
    }
    
    private long lcm(long a, long b) {
        return (a * b)/gcd(a, b);
    }
    
    private void update(int v, int tl, int tr, int index, int val) {
        if(tl > tr) {
            return;
        }
        
        if(tl == tr) {
            tree[v] = val;
            return; 
        }
        
        int tmid = (tl + tr)/2;
        
        if(index <= tmid) {
            update(2 * v, tl, tmid, index, val);
        } else {
            update(2 * v + 1, tmid + 1, tr, index, val);
        }
        
        tree[v] = lcm(tree[2 * v], tree[2 * v + 1]);
    }
    
    private long range(int v, int tl, int tr, int ql, int qr) {
        if(ql > qr) {
            return 1;
        }
        
        if(tl >= ql && tr <= qr) {
            return tree[v];
        }
        
        int tmid = (tl + tr)/2;
        
        long leftLCM = range(2 * v, tl, tmid, ql, Math.min(tmid, qr));
        long rightLCM = range(2 * v + 1, tmid + 1, tr, Math.max(tmid + 1, ql), qr);
        
        return lcm(leftLCM, rightLCM);
    }
    
    public void update(int i, int val) {
        update(1, 0, n - 1, i, val);
    }
    
    public long range(int l, int r) {
        return range(1, 0, n - 1, l, r);
    }
}

class Solution {
    public ArrayList<Long> RangeLCMQuery(int[] arr, int[][] queries) {
        int n = arr.length;
        
        LCMSegmentTree tree = new LCMSegmentTree(n, arr);
        
        ArrayList<Long> res = new ArrayList<>();
        
        for(int[] query : queries) {
            if(query[0] == 1) {
                tree.update(query[1], query[2]);
            } else {
                res.add((long) tree.range(query[1], query[2]));
            }
        }
        
        return res;
    }
}