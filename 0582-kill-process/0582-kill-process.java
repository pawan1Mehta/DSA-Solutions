class Solution {

    private void killProcessUtil(int currPid, boolean isNoParent, Map<Integer, List<Integer>> adjList, int kill, List<Integer> res) {
        if(currPid == kill) {
            isNoParent = true;
        }

        if(isNoParent) {
            res.add(currPid);
        }
        
        if(!adjList.containsKey(currPid) || adjList.get(currPid).size() == 0) {
            return;
        }

        for(int childProcess : adjList.get(currPid)) {
            killProcessUtil(childProcess, isNoParent, adjList, kill, res);
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        int parentProcess = -1;

        for(int i = 0; i < pid.size(); i++) {
            if(ppid.get(i) == 0) {
                parentProcess = pid.get(i);
                continue;
            }

            adjList.putIfAbsent(ppid.get(i), new ArrayList<>());
            adjList.get(ppid.get(i)).add(pid.get(i));
        }

        List<Integer> res = new ArrayList<>();

        killProcessUtil(parentProcess, false, adjList, kill, res);

        return res;
    }
}