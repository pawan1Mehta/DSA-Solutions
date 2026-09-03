class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();

        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for(int i = 0; i < n; i++) {
            lastOccurrence.put(s.charAt(i), i);
        }

        Stack<Character> st = new Stack<>();
        Set<Character> includedChar = new HashSet<>();

        for(int i = 0; i < n; i++) {
            if(!includedChar.contains(s.charAt(i))) {
                while(!st.isEmpty() 
                        && st.peek() > s.charAt(i)
                        && lastOccurrence.get(st.peek()) > i) {
                    includedChar.remove(st.pop());
                }

                st.push(s.charAt(i));
                includedChar.add(s.charAt(i));
            }
        }

        StringBuilder resStr = new StringBuilder();
        while(!st.isEmpty()) {
            resStr.append(st.pop());
        }

        return resStr.reverse().toString();
    }
}