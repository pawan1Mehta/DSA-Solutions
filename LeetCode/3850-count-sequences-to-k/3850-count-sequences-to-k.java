import java.math.BigInteger;
import java.util.Objects;

class Fraction {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) {
        if(denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        BigInteger gcd = numerator.gcd(denominator);

        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    public Fraction multiply(int val) {
        return new Fraction(
            numerator.multiply(BigInteger.valueOf(val)), 
            denominator
        );
    }

    public Fraction divide(int val) {
        if(val == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Fraction(
            numerator,
            denominator.multiply(BigInteger.valueOf(val))
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Fraction num = (Fraction) obj;

        return numerator.equals(num.numerator) &&  denominator.equals(num.denominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

class Solution {
    public int countSequences(int[] nums, long k) {
        
        Map<Fraction, Integer> dp = new HashMap<>();
        dp.put(new Fraction(BigInteger.valueOf(1), BigInteger.valueOf(1)), 1);

        for(int num : nums) {
            Map<Fraction, Integer> newDp = new HashMap<>();

            for(Map.Entry<Fraction, Integer> data : dp.entrySet()) {
                Fraction key = data.getKey();
                int count = data.getValue();

                newDp.put(
                    key.multiply(num),
                    newDp.getOrDefault(key.multiply(num), 0) + count
                );

                newDp.put(
                    key.divide(num),
                    newDp.getOrDefault(key.divide(num), 0) + count
                );

                newDp.put(
                    key,
                    newDp.getOrDefault(key, 0) + count
                );
            }

            dp = newDp;
        }

        return dp.getOrDefault(new Fraction(BigInteger.valueOf(k), BigInteger.valueOf(1)), 0);
    }
}