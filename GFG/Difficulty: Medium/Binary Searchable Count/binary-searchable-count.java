class Solution {
    
    private int binarySearch(int[] arr, int num) {
        int n = arr.length;
        
        int l = 0, r = n - 1;
        int resIndx = -1;
        
        while(l <= r) {
            int mid = (l + r)/2;
            
            if(arr[mid] == num) {
                resIndx = mid;
                break;
            }
            
            if(arr[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return resIndx;
    }
    
    public int binarySearchable(int[] arr) {
        int count = 0;
        
        for(int num : arr) {
            if(binarySearch(arr, num) != -1) {
                count++;
            }
        }
        
        return count;
    }
};
/*
    
    Note:
        1.) find the maximum count of integers
        2.) 
    
    
    ex:
    
        1.) arr[] = [2, 1, 3, 5, 4, 6]
                     0  1  2  3  4  5
                           |
                           
                     
                     mid = 0 + 1/2 = 1
                     
    
    
    case where we should stop
    
*/