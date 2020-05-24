class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null )
            return l1;

        for (; l1 != null; l1 = l1.next) {
            ListNode p = new ListNode(l1.val);

            // 插入的在头节点之前
            if (l2.val > p.val) {
                p.next = l2;
                l2 = p;
            } else {
                // 插入的在头节点之后
                for (ListNode t = l2; t != null; t = t.next) {
                    if (t.next == null || t.next.val > p.val) {
                        p.next = t.next;
                        t.next = p;
                        break;
                    }
                }
            }
        }

        return l2;
    }
}