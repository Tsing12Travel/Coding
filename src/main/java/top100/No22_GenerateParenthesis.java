package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No22_GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(n, n, path, res);
        return res;
    }

    private void dfs(int left, int right, StringBuilder path, List<String> res) {
        if (left == 0 && right == 0) {  // 左右都到头了，说明有效括号已经构建完成
            res.add(path.toString());
            return;
        }

        // 剪枝(把不符合要求的剪去，如右括号在左括号左边的各种组合，可以注释掉这行对比输出结果)
        if (left > right) return;

        if (left > 0) {
            path.append("(");
            dfs(left - 1, right, path, res);
            path.deleteCharAt(path.length() - 1);
        }

        if (right > 0) {
            path.append(")");
            dfs(left, right - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }


    // 枚举填左括号还是右括号
    private int n;
    private char[] path;
    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis2(int n) {
        this.n = n;
        path = new char[n * 2];
        dfs2(0, 0);
        return ans;
    }

    private void dfs2(int i, int open) {
        if (i == n * 2) {
            ans.add(new String(path));
            return;
        }

        if (open < n) {  // 可以填左括号
            path[i] = '(';
            dfs2(i + 1, open + 1);
        }

        if (i - open < open) {  // 可以填右括号
            path[i] = ')';
            dfs2(i + 1, open);
        }
    }


    // 枚举下一个左括号的位置
    private int n3;
    private final List<Integer> path3 = new ArrayList<>();
    private final List<String> ans3 = new ArrayList<>();

    public List<String> generateParenthesis3(int n3) {
        this.n3 = n3;
        dfs3(0, 0);
        return ans3;
    }

    // balance = 左括号个数 - 右括号个数
    private void dfs3(int i, int balance) {
        if (path3.size() == n3) {
            // 注意：path3 里面存的是左括号所在的下标，但最终输出是组合情况，故二者需要转换
            char[] newPath = new char[n3 * 2];
            Arrays.fill(newPath, ')');
            for (int j : path3) newPath[j] = '(';
            ans3.add(new String(newPath));
            return;
        }

        // 可以填 0 到 balance 个右括号
        for (int close = 0; close <= balance; close++) {  // 填 close 个右括号
            path3.add(i + close);  // 填 1 个左括号
            dfs3(i + close + 1, balance - close + 1);
            path3.removeLast();
        }
    }


    public static void main(String[] args) {
        No22_GenerateParenthesis sol = new No22_GenerateParenthesis();
        System.out.println(sol.generateParenthesis3(3));
    }
}
