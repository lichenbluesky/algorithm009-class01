class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int j = 0; j < nums.length; ++j) {
            int expect = target - nums[j];
            if (map.containsKey(expect)) {
                return new int[] {(int)map.get(expect),j};
            }
            map.put(nums[j], j);
        }
        return null;
    }
}