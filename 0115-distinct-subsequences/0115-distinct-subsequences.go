var memo [][]int

func solve(i, j int, s, t string) int {
    if i == len(s) || j == len(t) {
        if j == len(t) {
            return 1
        }
        return 0
    }

    if memo[i][j] != -1 {
        return memo[i][j]
    }

    count := 0

    if s[i] == t[j] {
        count += solve(i + 1, j + 1, s, t)
    }
    count += solve(i + 1, j, s, t)

    memo[i][j] = count

    return memo[i][j]
}

func numDistinct(s string, t string) int {
    n := len(s)
    m := len(t)

    memo = make([][]int, n)
    for i := 0; i < n; i++ {
        memo[i] = make([]int, m)
    }

    for i := 0; i < n; i++ {
        for j := 0; j < m; j++ {
            memo[i][j] = -1
        }
    }

    return solve(0, 0, s, t)
}