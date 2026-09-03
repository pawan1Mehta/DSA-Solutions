func singleNumber(nums []int) []int {
    xorNum := 0;

    for _, num := range(nums) {
        xorNum = xorNum ^ num;
    }

    rightMostBit := 1;
    for ((xorNum & rightMostBit) == 0) {
        rightMostBit = rightMostBit << 1;
    }

    firstNum := 0
    for _, num := range(nums) {
        if (num & rightMostBit) != 0 {
            firstNum = firstNum ^ num;
        }
    }

    secondNum := xorNum ^ firstNum;

    return []int{firstNum, secondNum};
}