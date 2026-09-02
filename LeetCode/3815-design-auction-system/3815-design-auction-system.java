class AuctionSystem {

    Map<Integer, Map<Integer, Integer>> userItemBids; 
    Map<Integer, Map<Integer, SortedSet<Integer>>> itemBids;

    public AuctionSystem() {
        userItemBids = new HashMap<>();
        itemBids = new HashMap<>();
    }
    
    public void addBid(int userId, int itemId, int bidAmount) {
        if(!userItemBids.containsKey(userId)) {
            userItemBids.put(userId, new HashMap<>());
        }

        Map<Integer, Integer> currUserItemBids = userItemBids.get(userId);

        // Remove any previous bids
        if(currUserItemBids.containsKey(itemId)) {
            int prevAmount = currUserItemBids.get(itemId);
            removeItemBids(itemId, userId, prevAmount);
        }

        // Add new bids
        currUserItemBids.put(itemId, bidAmount);
        addItemBids(itemId, userId, bidAmount);
    }
    
    public void updateBid(int userId, int itemId, int newAmount) {
        Map<Integer, Integer> currUserItemBids = userItemBids.get(userId);

        // Remove the previous bid
        int prevAmount = currUserItemBids.get(itemId);
        removeItemBids(itemId, userId, prevAmount);

        // Update the bid
        currUserItemBids.put(itemId, newAmount);
        addItemBids(itemId, userId, newAmount);
    }
    
    public void removeBid(int userId, int itemId) {
        Map<Integer, Integer> currUserItemBids = userItemBids.get(userId);

        // Remove the previous bid
        int prevAmount = currUserItemBids.get(itemId);
        removeItemBids(itemId, userId, prevAmount);

        currUserItemBids.remove(itemId);
    }
    
    public int getHighestBidder(int itemId) {
        if(!itemBids.containsKey(itemId)) {
            return -1;
        }
        
        TreeMap<Integer, SortedSet<Integer>> itemAmountUserBitMap = (TreeMap<Integer, SortedSet<Integer>>) itemBids.get(itemId);
        int topBidAmount = itemAmountUserBitMap.lastKey();
        return itemAmountUserBitMap.get(topBidAmount).last();
    }

    private void addItemBids(int itemId, int userID, int bidAmount) {
        if(!itemBids.containsKey(itemId)) {
            itemBids.put(itemId, new TreeMap<>());
        }

        Map<Integer, SortedSet<Integer>> itemAmountUserBitMap = itemBids.get(itemId);
        
        if(!itemAmountUserBitMap.containsKey(bidAmount)) {
            itemAmountUserBitMap.put(bidAmount, new TreeSet<>());
        }
        
        itemAmountUserBitMap.get(bidAmount).add(userID);
    }

    private void removeItemBids(int itemId, int userID, int bidAmount) {
        Map<Integer, SortedSet<Integer>> itemAmountUserBitMap = itemBids.get(itemId);
            
        itemAmountUserBitMap.get(bidAmount).remove(userID);

        if(itemAmountUserBitMap.get(bidAmount).size() == 0) {
            itemAmountUserBitMap.remove(bidAmount);
        }

        if(itemAmountUserBitMap.size() == 0) {
            itemBids.remove(itemId);
        }
    }
}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem obj = new AuctionSystem();
 * obj.addBid(userId,itemId,bidAmount);
 * obj.updateBid(userId,itemId,newAmount);
 * obj.removeBid(userId,itemId);
 * int param_4 = obj.getHighestBidder(itemId);
 */