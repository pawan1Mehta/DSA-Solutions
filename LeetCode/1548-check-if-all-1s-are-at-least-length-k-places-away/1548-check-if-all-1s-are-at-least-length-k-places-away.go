func kLengthApart(nums []int, k int) bool {
    n := len(nums)
    
    lastOneIndex := -1;

    for i := 0; i < n; i++ {
        if nums[i] == 1 {
            if(lastOneIndex != -1 && (i - lastOneIndex - 1) < k) {
                return false;
            }

            lastOneIndex = i;
        }
    }

    return true;
}