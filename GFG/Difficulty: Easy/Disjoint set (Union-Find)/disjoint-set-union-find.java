class GfG {
    int find(int par[], int x) {
        int root = x;
        while(par[root] != root) {
            root = par[root];
        }
        return root;
    }

    void unionSet(int par[], int x, int z) {
        int root1 = find(par, x);
        int root2 = find(par, z);
        
        if(root1 != root2) {
            par[root1] =  root2;
        }
    }
}