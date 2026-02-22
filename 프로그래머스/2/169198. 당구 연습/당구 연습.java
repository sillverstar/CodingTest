class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        // ball 선택
        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
            int minDist = Integer.MAX_VALUE;
            // 상하좌우 탐색
            if (!(startY == targetY && startX >= targetX)) {
                int cur = getDist(startX, startY, targetX * (-1), targetY); // y=0 대칭(좌)
                minDist = Math.min(minDist, cur);
            }
            
            if (!(startY == targetY && startX <= targetX)) {
                int cur = getDist(startX, startY, m + (m - targetX), targetY); // y=m 대칭(우)
                minDist = Math.min(minDist, cur);
            }
            
            if (!(startX == targetX && startY <= targetY)) {
                int cur = getDist(startX, startY, targetX, n + (n - targetY)); // x=n 대칭(상)
                minDist = Math.min(minDist, cur);
            }
            
            if (!(startX == targetX && startY >= targetY)) { 
                int cur = getDist(startX, startY, targetX, targetY * (-1)); // x=0 대칭(하)
                minDist = Math.min(minDist, cur);
            }
            
            answer[i] = minDist;
        }
        return answer;
    }
    public int getDist(int sx, int sy, int tx, int ty) {
    return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
}
}

