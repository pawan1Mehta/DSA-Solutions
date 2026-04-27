class SegmentTree {
    int n ;
    long[] increment;
    long[] decrement;

    public SegmentTree(int n) {
        this.n = n;
        increment = new long[4 * n];
        decrement = new long[4 * n];
    }

    public void updateIncr(int v, int tl, int tr, int index, long val) {
        if(tl == tr) {
            increment[v] = Math.max(increment[v], val);
            return;
        }

        int mid = (tl + tr)/2;

        if(index <= mid) {
            updateIncr(2 * v, tl, mid, index, val);
        } else {
            updateIncr(2 * v + 1, mid + 1, tr, index, val);
        }

        increment[v] = Math.max(increment[2 * v], increment[2 * v + 1]);
    }

    public long queryIncr(int v, int tl, int tr, int l, int r) {
        if(l <= tl && tr <= r) {
            return increment[v];
        }

        if(l > tr || r < tl) {
            return 0;
        }

        int mid = (tl + tr)/2;

        long left = queryIncr(2 * v, tl, mid, l, r);
        long right = queryIncr(2 * v + 1, mid + 1, tr, l, r);

        return Math.max(left, right);
    }
    
    public void updateIncrement(int i, long val) {
        updateIncr(1, 0, n - 1, i, val);
    }

    public long queryIncrement(int l, int r) {
        return queryIncr(1, 0, n - 1, l, r);
    }

    public void updateDecr(int v, int tl, int tr, int index, long val) {
        if(tl == tr) {
            decrement[v] = Math.max(decrement[v], val);
            return;
        }

        int mid = (tl + tr)/2;

        if(index <= mid) {
            updateDecr(2 * v, tl, mid, index, val);
        } else {
            updateDecr(2 * v + 1, mid + 1, tr, index, val);
        }

        decrement[v] = Math.max(decrement[2 * v], decrement[2 * v + 1]);
    }

    public long queryDecr(int v, int tl, int tr, int l, int r) {
        if(l <= tl && tr <= r) {
            return decrement[v];
        }

        if(l > tr || r < tl) {
            return 0;
        }

        int mid = (tl + tr)/2;

        long left = queryDecr(2 * v, tl, mid, l, r);
        long right = queryDecr(2 * v + 1, mid + 1, tr, l, r);

        return Math.max(left, right);
    }

    public void updateDecrement(int i, long val) {
        updateDecr(1, 0, n -1, i, val);
    }

    public long queryDecrement(int l, int r) {
        return queryDecr(1, 0, n - 1, l, r);
    }
}

class Solution {
    public long maxAlternatingSum(int[] nums, int k) {
        int maxVal = 0;

        for(int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        SegmentTree sgt = new SegmentTree(maxVal + 1);
        
        Queue<long[]> iq = new LinkedList<>();
        Queue<long[]> dq = new LinkedList<>();
        
        long maxScore = 0;

        for(int i = nums.length - 1; i >= 0; i--) {
            int currVal = nums[i];

            if(iq.size() == k) {
                long[] val = iq.poll();
                sgt.updateIncrement((int) val[0], val[1]);
            }
            if(dq.size() == k) {
                long[] val = dq.poll();
                sgt.updateDecrement((int) val[0], val[1]);
            }

            long incre = sgt.queryIncrement(0, currVal - 1);
            dq.add(new long[]{currVal, currVal + incre});

            long decre = sgt.queryDecrement(currVal + 1, maxVal);
            iq.add(new long[]{currVal, currVal + decre});

            maxScore = Math.max(maxScore, currVal + incre);
            maxScore = Math.max(maxScore, currVal + decre);
        }

        return maxScore;
    }   
}