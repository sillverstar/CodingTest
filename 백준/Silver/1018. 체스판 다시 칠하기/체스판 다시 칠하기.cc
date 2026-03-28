#include <stdio.h>
#include <stdbool.h>

#ifndef min3
#define min3(a, b, c) ( ((a) < (b)) ? ( ((a) < (c)) ? (a) : (c)) : ( ((b) < (c)) ? (b) : (c)))
#endif

#define MAX_N 50 // 세로(N)
#define MAX_M 50 // 가로(M)

int main() {
    int N, M;
    char board[MAX_N][MAX_M + 1];

    scanf("%d %d", &N, &M);
    for (int i = 0; i < N; i++) {
        scanf("%s", board[i]);
    }


    bool first, expected, actual;
    int incorrect, answer = 64;

    for (int i = 0; i <= N-8; i++) {
        for (int j = 0; j <= M-8; j++) { // (i, j): 시작점
            incorrect = 0;
            first = (board[i][j] == 'W'); // W면 True(1), B이면 False(0)
            for (int r = i; r < i+8; r++) {
                for (int c = j; c < j+8; c++) { // (r, c): 블록 내부
                    // printf("%c ", board[r][c]);

                    expected = ((r + c) % 2 == (i + j) % 2) ? first : !first;
                    actual = (board[r][c] == 'W');

                    if (expected != actual) {
                        incorrect++;
                    }
                }
                // printf("\n");
            }
            answer = min3(answer, incorrect, 64-incorrect);
            // printf("min: %d\n", incorrect);
            // printf("ans: %d\n", answer);

        }
        // printf("\n");
    }

    printf("%d\n", answer);

    return 0;

}