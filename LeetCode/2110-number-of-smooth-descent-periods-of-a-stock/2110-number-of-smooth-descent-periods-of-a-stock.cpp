class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
       
        int n=prices.size();
        
        if(n == 1)
            return 1;
        
        vector<long long> left(n,1);
        
        for(int i=1; i<n; i++)
        {
            if(prices[i-1] == prices[i]+1)
               left[i]=left[i-1]+1; 
        }
        
        long long total=n;
        
        for(int i=0; i<n-1; i++)
        {
            if(left[i] > left[i+1])
            {
                long long num=left[i]-1;
                long long sum=((num)*(num+1))/2;
                total+=sum;
            }
        }
        
        if(left[n-1] > left[n-2])
        {
            long long num=left[n-1]-1;
            long long sum=((num)*(num+1))/2;
            total+=sum;
        }
        
        return total;        
    }
};