class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> validIpsList = new ArrayList<>();
        constructValidIps(0, 0, "", validIpsList, s);
        return validIpsList;
    }

    private void constructValidIps(int i, int dot, String currIp, List<String> validIPs, String str) {
        if(dot == 3) {
            if(i < str.length()) {
                return;
            } else {
                validIPs.add(currIp);
            }
            return;
        }

        StringBuilder currPart = new StringBuilder();

        for(int j = i; j < Math.min(i + 3, str.length()); j++) {
            currPart.append(str.charAt(j));
            if(isValidIpPart(currPart.toString())) {
                String newStr;
                int newDot = 0;
                if(currIp.length() == 0) {
                    newStr = currPart.toString();
                    newDot = 0;
                } else {
                    newStr = currIp + "." + currPart.toString();
                    newDot = dot + 1;
                }
                constructValidIps(j + 1, newDot, newStr, validIPs, str);
            }
        }
    }
    
    private boolean isValidIpPart(String str) {
        int n = str.length();

        if(n > 1 && str.charAt(0) == '0') {
            return false;
        }

        int num = Integer.parseInt(str);

        return (num >= 0 && num <= 255) ? true : false;
    }
}