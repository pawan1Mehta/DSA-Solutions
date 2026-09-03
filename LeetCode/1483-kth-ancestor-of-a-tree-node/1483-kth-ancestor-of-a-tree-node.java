class Node {
    int node;
    Node[] up;

    public Node(int node) {
        this.node = node;
        this.up = new Node[20];
    }
}

class TreeAncestor {
    private Node[] nodes;

    private void binaryLifting() {
        for(int p = 1; p < 20; p++) {
            for(Node node : nodes) {
                if(node.up[p - 1] != null) {
                    node.up[p] = node.up[p - 1].up[p - 1];
                }
            }
        }
    }

    private Node goUp(Node node, int nSteps) {
        if(node == null) {
            return null;
        }
        
        if(nSteps == 0) {
            return node;
        }

        int largestPower = 1;
        int idx = 0;

        while(2 * largestPower <= nSteps) {
            largestPower = 2 * largestPower;
            idx++;
        }

        Node nextNode = node.up[idx];

        return goUp(nextNode, nSteps - largestPower);
    }

    public TreeAncestor(int n, int[] parent) {
        nodes = new Node[n];

        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        nodes[0].up[0] = null;

        for(int i = 1; i < n; i++) {
            nodes[i].up[0] = nodes[parent[i]];
        }
        
        binaryLifting();
    }
    
    public int getKthAncestor(int node, int k) {
        Node kthAncestor = goUp(nodes[node], k);

        if(kthAncestor == null) {
            return -1;
        }

        return kthAncestor.node;
    }
}


/*

         0
       /    \
    1        2
   /           \
 3              4

*/