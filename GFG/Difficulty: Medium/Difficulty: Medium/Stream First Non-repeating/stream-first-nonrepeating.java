class Solution {
    public String firstNonRepeating(String s) {
        int n = s.length();
        
        Map<Character, Integer> freq = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        
        StringBuilder nonRepeatingStr = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            queue.add(ch);
            
            while(!queue.isEmpty() && freq.get(queue.peek()) > 1) {
                queue.poll();
            }
            
            if(queue.isEmpty()) {
                nonRepeatingStr.append('#');
            } else {
                nonRepeatingStr.append(queue.peek());
            }
        }
        
        return nonRepeatingStr.toString();
    }
}