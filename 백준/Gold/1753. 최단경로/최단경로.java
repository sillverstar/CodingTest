import java.util.*;
import java.io.*;

class Edge {
    int to;
    int w;
    
    public Edge(int to, int w) {
        this.to = to;
        this.w = w;
    }
}

public class Main {
    static int v, e, k;
    static List<List<Edge>> graph;
    static int[] dist;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 정점의 개수 v, 간선의 개수 e 입력
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 시작 정점
        k = Integer.parseInt(br.readLine());
        
        // 그래프 선언 + 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        
        dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
    
        
        //간선 입력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new Edge(v, w));
        }
        
        dijkstra();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
        	int ans = dist[i];
            sb.append((ans == Integer.MAX_VALUE)? "INF" : ans).append('\n');
        }
        
        System.out.println(sb);
        
        
    }

    private static void dijkstra() {
        // 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 두 번째 값(누적 비용) 기준으로 오른차순[낮은 값부터 poll]
        
        // 초기값 넣기 (현재 정점, 누적 비용)
        pq.offer(new int[] {k, 0});
        dist[k] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int len = cur[1];
            
            // 갱신되기 전 값이면 continue
            if (len != dist[now]) continue;
            
            for (Edge next : graph.get(now)) {
                int nextLen = len + next.w;
                if (dist[next.to] > nextLen) {
                    pq.offer(new int[] {next.to, nextLen});
                    dist[next.to] = nextLen; 
                }
            }
        }
    }

}
