class Solution {
    List<Integer>[] tree;
    int[] last;
    int[] answer;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        tree = new ArrayList[n];
        last = new int[26];
        answer = new int[n];

        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        int[] newParent = parent.clone();
        dfs1(0, newParent);

        for (int i = 0; i < n; i++) {
            tree[i].clear();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        dfs2(0);

        return answer;
    }

    void dfs1(int node, int[] parent) {
        int index = s.charAt(node) - 'a';
        int prev = last[index];

        if (last[index] != -1) {
            parent[node] = last[index];
        }

        last[index] = node;

        for (int child : tree[node]) {
            dfs1(child, parent);
        }

        last[index] = prev;
    }

    void dfs2(int node) {
        answer[node] = 1;

        for (int child : tree[node]) {
            dfs2(child);
            answer[node] += answer[child];
        }
    }
}