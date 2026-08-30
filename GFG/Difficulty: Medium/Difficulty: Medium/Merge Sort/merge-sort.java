class Solution {
    
    void merge(int[] arr, int l, int mid, int r) {
        ArrayList<Integer> sorteList = new ArrayList<>();
        
        int i = l, j = mid + 1;
        while(i <= mid && j <= r) {
            if(arr[i] < arr[j]) {
                sorteList.add(arr[i]);
                i++;
            } else {
                sorteList.add(arr[j]);
                j++;
            }
        }
        
        while(i <= mid) {
            sorteList.add(arr[i]);
            i++;
        }
        
        while(j <= r) {
            sorteList.add(arr[j]);
            j++;
        }
        
        for(int k = 0, idx = l; k < sorteList.size(); k++, idx++) {
            arr[idx] = sorteList.get(k);
        }
    }

    void mergeSort(int arr[], int l, int r) {
        if(l < r) {
            int mid = (l + r)/2;
            
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            
            merge(arr, l, mid, r);
        }
    }
}