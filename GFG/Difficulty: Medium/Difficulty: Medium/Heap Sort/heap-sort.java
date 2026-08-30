class Solution {
    
    public void heapSort(int arr[]) {
        int n = arr.length;
        
        /* 
         Why  n/2 -1 ?
         
         In a heap array, roughly the second half of the elements  are leaf nodes; they have no children.
        */
        for(int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        
        for(int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }
    
    private void heapify(int[] arr, int rootIndex, int n) {
        int largestNodeIndex = rootIndex;
        
        int leftChild = (2 * rootIndex) + 1;
        int rightChild = (2 * rootIndex) + 2;
        
        if(leftChild < n && arr[leftChild] > arr[largestNodeIndex]) {
            largestNodeIndex = leftChild;
        }
        
        if(rightChild < n && arr[rightChild] > arr[largestNodeIndex]) {
            largestNodeIndex = rightChild;
        }
        
        if(largestNodeIndex != rootIndex) {
            swap(arr, rootIndex, largestNodeIndex);
            heapify(arr, largestNodeIndex, n);
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}