class Solution {

    private int[] minFlips(TreeNode root) {
        if(root.val == 0) {
            return new int[]{1, 0};
        }

        if(root.val == 1) {
            return new int[]{0, 1};
        }

        if(root.val == 5) {
            if(root.left != null) {
                int[] val = minFlips(root.left);
                return new int[]{val[1], val[0]};
            } else {
                int[] val = minFlips(root.right);
                return new int[]{val[1], val[0]};
            }
        }

        int[] left = minFlips(root.left);
        int[] right = minFlips(root.right);

        if(root.val == 2) {
            int trueMinCount = Math.min(
                left[0] + right[1], 
                Math.min(
                    left[1] + right[0], 
                    left[0] + right[0]
                )
            );

            int falseMinCount = left[1] + right[1];

            return new int[]{trueMinCount, falseMinCount};
        } else if(root.val == 3) {
            int trueMinCount = left[0] + right[0];

            int falseMinCount = Math.min(
                left[0] + right[1], 
                Math.min(
                    left[1] + right[0], 
                    left[1] + right[1]
                )
            );

            return new int[]{trueMinCount, falseMinCount};
        } else {
            int trueMinCount = Math.min(
                left[0] + right[1], 
                left[1] + right[0]
            );

            int falseMinCount = Math.min(
                left[1] + right[1], 
                left[0] + right[0]
            );

            return new int[]{trueMinCount, falseMinCount};
        }
    }

    public int minimumFlips(TreeNode root, boolean result) {
        int[] res = minFlips(root);

        if(result == true) {
            return res[0];
        } else {
            return res[1];
        }
    }
}