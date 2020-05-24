class Solution {
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; ++i) {
            //向后移动一个元素
            int start = nums[0];
            for (int j = 0; j < nums.length; ++j) {
                int temp = nums[(j + 1) % nums.length];
                nums[(j + 1) % nums.length] = start;
                start = temp;
            }
        }
    }
}