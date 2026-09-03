func minBitFlips(start int, goal int) int {
    count := 0

    for i:= 0; i < 32; i++ {
        mask := 1 << i
        if (start & mask) != (goal & mask) {
            count++
        }
    }

    return count
}