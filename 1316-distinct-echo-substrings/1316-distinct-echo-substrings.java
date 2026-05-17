class RabinKarp {
    private final long mod1 = 1_000_000_007;
    private final long mod2 = 1_000_000_009;

    private final long base1 = 31;
    private final long base2 = 37;

    private long[] hash1;
    private long[] hash2;
    
    private long[] pow1;
    private long[] pow2;

    RabinKarp(String str) {
        hash(str);
    }
    
    private void hash(String str) {
        int n = str.length();

        hash1 = new long[n];
        hash2 = new long[n];

        pow1 = new long[n];
        pow2 = new long[n];

        hash1[0] = charToLong(str.charAt(0));
        hash2[0] = charToLong(str.charAt(0));
        
        pow1[0] = 1;
        pow2[0] = 1;

        for(int i = 1; i < n; i++) {
            hash1[i] = add(multi(hash1[i - 1], base1, mod1), charToLong(str.charAt(i)), mod1);
            pow1[i] = multi(pow1[i - 1], base1, mod1);

            hash2[i] = add(multi(hash2[i - 1], base2, mod2), charToLong(str.charAt(i)), mod2);
            pow2[i] = multi(pow2[i - 1], base2, mod2);
        }
    }

    public long[] getHash(int l, int r) {
        long hashVal1 = hash1[r];
        long hashVal2 = hash2[r];

        if(l > 0) {
            hashVal1 = sub(hashVal1, multi(hash1[l - 1], pow1[r - l + 1], mod1), mod1);
            hashVal2 = sub(hashVal2, multi(hash2[l - 1], pow2[r - l + 1], mod2), mod2);
        }

        return new long[]{hashVal1, hashVal2};
    }

    private long charToLong(char ch) {
        return (long) (ch - 'a' + 1);
    }

    private long add(long a, long b, long mod) {
        a += b;
        if(a >= mod) {
            a -= mod;
        }
        return a;
    }

    private long sub(long a, long b, long mod) {
        a -= b;
        if(a < 0) {
            a += mod;
        }
        return a;
    }

    private long multi(long a, long b, long mod) {
        return (a * b) % mod;
    }
}

class Solution {
    public int distinctEchoSubstrings(String text) {
        int n = text.length();

        Set<String> seen = new HashSet<>();

        RabinKarp textHash = new RabinKarp(text);

        for(int len = 1; len <= (n/2); len++) {
            for(int i = 0; (i + 2 * len) <= n; i++) {
                long[] first = textHash.getHash(i, i + len - 1);
                long[] second = textHash.getHash(i + len, i + (2 * len) - 1);

                if(first[0] == second[0] && first[1] == second[1]) {
                    long[] full = textHash.getHash(i, i + (2 * len) - 1);
                    seen.add(full[0] + ":" + full[1]);
                }
            }
        }

        return seen.size();
    }
}