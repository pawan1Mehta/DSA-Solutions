/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> st = new HashSet<>();
        for(int num : nums) {
            st.add(num);
        }

        ListNode newHead = new ListNode(-1);
        ListNode node = head;
        ListNode prevNode = newHead;

        while(node != null) {
            if(st.contains(node.val)) {
                prevNode.next = node.next;
            } else {
                prevNode.next = node;
                prevNode = node;
            }
            node = node.next;
        }

        return newHead.next;
    }
}