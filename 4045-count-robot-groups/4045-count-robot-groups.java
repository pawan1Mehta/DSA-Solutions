class Solution {
    public int countGroups(int[] position, int[] speed, int distance) {
        int n = position.length;

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{position[0], speed[0]});

        for(int i = 1; i < n; i++) {
            int lastIdx = list.size() - 1;
            int pos = list.get(lastIdx)[0];
            if((position[i] - pos) <= distance) {
                list.set(lastIdx, new int[]{position[i], speed[i]});
            } else {
                list.add(new int[]{position[i], speed[i]});
            }
        }

        Stack<Integer> st = new Stack<>();
        st.add(list.get(0)[1]);
            
        for(int i = 1; i < list.size(); i++) {
            int currSpeed = list.get(i)[1];
            
            while(!st.isEmpty() && st.peek() > currSpeed) {
                st.pop();   
            }

            st.add(currSpeed);
        }

        return st.size();
    }
}