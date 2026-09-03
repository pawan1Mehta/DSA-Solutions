class Solution {
    public List<String> validateCoupons(String[] codes, String[] businessLines, boolean[] isActive) {
        List<String> validElectronics = new ArrayList<>();
        List<String> validGrocery = new ArrayList<>();
        List<String> validPharmacy = new ArrayList<>();
        List<String> validRestaurant = new ArrayList<>();
        
        for (int i = 0; i < codes.length; i++) {
            String currentCode = codes[i];
            if (currentCode.isEmpty()) {
                continue;
            }
            if (!isValidCode(currentCode)) {
                continue;
            }
            if (isActive[i]) {
                String businessLine = businessLines[i].toLowerCase();
                switch (businessLine) {
                    case "electronics":
                        validElectronics.add(currentCode);
                        break;
                    case "grocery":
                        validGrocery.add(currentCode);
                        break;
                    case "pharmacy":
                        validPharmacy.add(currentCode);
                        break;
                    case "restaurant":
                        validRestaurant.add(currentCode);
                        break;
                }
            }
        }

        Collections.sort(validElectronics);
        Collections.sort(validGrocery);
        Collections.sort(validPharmacy);
        Collections.sort(validRestaurant);
        
        List<String> result = new ArrayList<>();
        result.addAll(validElectronics);
        result.addAll(validGrocery);
        result.addAll(validPharmacy);
        result.addAll(validRestaurant);
        
        return result;
    }
    
    private boolean isValidCode(String code) {
        for (char c : code.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}