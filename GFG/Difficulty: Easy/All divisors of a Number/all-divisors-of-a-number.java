class Solution {
    public static void print_divisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        for(int num = 1; num <= Math.sqrt(n); num++) {
            if(n%num == 0) {
                int a = num;
                int b = n/a;
                
                divisors.add(num);
                if(a != b) {
                    divisors.add(b);
                }
            }
        }
        
        Collections.sort(divisors);
        
        for(int num : divisors) {
            System.out.print(num + " ");
        }
    }
}
