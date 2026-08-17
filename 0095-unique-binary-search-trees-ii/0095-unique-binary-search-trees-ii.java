/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        
        if (start > end) {
            result.add(null);
            return result;
        }

        
        for (int rootValue = start; rootValue <= end; rootValue++) {

            
            List<TreeNode> leftTrees = generate(start, rootValue - 1);

            
            List<TreeNode> rightTrees = generate(rootValue + 1, end);

            
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {

                    TreeNode root = new TreeNode(rootValue);

                    root.left = left;
                    root.right = right;

                    result.add(root);
                }
            }
        }

        return result;
    }
}