class Solution {
	
     /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        List left = preorderTraversal(root.left);
        List right = preorderTraversal(root.right);
        result.addAll(left);
        result.addAll(right);

        return result;
    }

    /**
     * 非递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                list.add(p.val);
                // 根节点入栈
                stack.push(p);
                p = p.left;
            } else {
                TreeNode t = stack.pop();
                p = t.right;
            }
        }
        return list;
    }
}