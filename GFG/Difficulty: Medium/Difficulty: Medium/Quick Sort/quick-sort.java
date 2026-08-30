class Solution {
    public void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(arr, low, high);
            
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotNum = arr[high];
        
        int index = low - 1;
        
        for(int j = low; j <= high; j++) {
            if(arr[j] < pivotNum) {
                index++;
                swap(arr, index, j);
            }
        }
        
        swap(arr, index + 1, high);
        
        return index + 1;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}