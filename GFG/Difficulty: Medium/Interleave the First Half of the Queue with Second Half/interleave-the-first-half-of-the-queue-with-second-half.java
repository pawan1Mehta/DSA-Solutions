class Solution {
    public void rearrangeQueue(Queue<Integer> q) {
        int size = q.size();
        
        Queue<Integer> first = new LinkedList<>();
        Queue<Integer> second = new LinkedList<>();
        
        for(int i = 0; i < (size/2); i++) {
            first.add(q.poll());
        }
        while(!q.isEmpty()) {
            second.add(q.poll());
        }
        
        boolean toggle = true;
        for(int i = 0; i < size; i++) {
            if(toggle) {
                q.add(first.poll());
                toggle = !toggle;
            } else {
                q.add(second.poll());
                toggle = !toggle;
            }
        }
    }
}