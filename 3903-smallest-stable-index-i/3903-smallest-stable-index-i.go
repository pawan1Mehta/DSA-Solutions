func firstStableIndex(nums []int, k int) int {
    n := len(nums)

    prefix := make([]int, n)
    suffix := make([]int, n)

    prefix[0] = nums[0]
    for i := 1; i < n; i++ {
        prefix[i] = max(prefix[i - 1], nums[i])
    }

    suffix[n - 1] = nums[n - 1]
    for i := n - 2; i >= 0; i-- {
        suffix[i] = min(suffix[i + 1], nums[i])
    }

    for i := 0; i < n; i++ {
        instabilityScore := prefix[i] - suffix[i]
        if instabilityScore <= k {
            return i
        }
    }

    return -1
}