class Solution {
public:
    int minimumBoxes(vector<int>& apple, vector<int>& capacity) {
        long long appleSum = 0;
        for(auto& a : apple){
            appleSum += a;
        }
        
        sort(capacity.begin(), capacity.end());
        reverse(capacity.begin(), capacity.end());
        
        int count = 0;
        for(auto& c : capacity){
            appleSum -= c;
            count++;
            if(appleSum <= 0){
                break;
            }
        }
        
        return count;
    }
};