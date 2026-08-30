
class MaxHeap {
    ArrayList<Integer> list;
    
    public MaxHeap() {
        list = new ArrayList<>();
    }
    
    private void add(int num) {
        list.add(num);
        
        int index = list.size() - 1;
        
        while(index > 0 && list.get((index - 1)/2) < list.get(index)) {
            int parentNodeVal = list.get((index - 1)/2);

            list.set((index - 1)/2, list.get(index));
            list.set(index, parentNodeVal);
            
            index = (index - 1)/2;
        }
    }
    
    private void heapify(int rootIndex) {
        int largestNodeIndex = rootIndex;
        
        int leftChildIndex = (2 * rootIndex) + 1;
        int rightChildIndex = (2 * rootIndex) + 2;
        
        if(leftChildIndex < list.size() 
            && list.get(leftChildIndex) > list.get(largestNodeIndex)) {
            largestNodeIndex = leftChildIndex; 
        }
        
        if(rightChildIndex < list.size() 
            && list.get(rightChildIndex) > list.get(largestNodeIndex)) {
            largestNodeIndex = rightChildIndex; 
        }
        
        if(largestNodeIndex != rootIndex) {
            swap(list, rootIndex, largestNodeIndex);
            heapify(largestNodeIndex);
        }
    }
    
    private void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    
    public void push(int num) {
        add(num);
    }
    
    public void pop() {
        swap(list, 0, list.size() - 1);
        list.remove(list.size() - 1);
        
        heapify(0);
    }
    
    public int peek() {
        return list.size() == 0 ? -1 : list.get(0);
    }
    
    public int size() {
        return list.size();
    }
}

class maxHeap {

    MaxHeap heap;
    
    public maxHeap() {
        heap = new MaxHeap();
    }

    public void push(int x) {
        heap.push(x);
    }

    public void pop() {
        heap.pop();
    }

    public int peek() {
        return heap.peek();
    }

    public int size() {
        return heap.size();
    }
}