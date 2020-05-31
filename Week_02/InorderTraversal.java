class Solution {
	
     /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }

        List left = inorderTraversal(root.left);
        result.addAll(left);
        result.add(root.val);
        List right = inorderTraversal(root.right);
        result.addAll(right);

        return result;
    }

    /**
     * 非递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                // 将根节点入栈
                stack.push(p);
                p = p.left;
            } else {
                // 左子树为空时则弹出根节点,访问右子树
                TreeNode t = stack.pop();
                list.add(t.val);
                p = t.right;
            }
        }
        return list;
    }
}