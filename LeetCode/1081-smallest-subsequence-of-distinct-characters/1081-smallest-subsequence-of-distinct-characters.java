class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();

        int ALPHABET_SIZE = 26;
        int[] index = new int[ALPHABET_SIZE];

        for(int i = 0; i < n; i++) {
            index[s.charAt(i) - 'a'] = i;
        }

        Stack<Character> st = new Stack<>();
        Set<Character> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && !set.contains(s.charAt(i)) && 
                    st.peek() > s.charAt(i) && index[st.peek() - 'a'] > i) {
                set.remove(st.pop());
            }
            if(!set.contains(s.charAt(i))) {
                st.push(s.charAt(i));
                set.add(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }
}