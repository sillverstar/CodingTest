white = [[0 for i in range(100)] for j in range(100)]

count = int(input())

for i in range(count):
    x, y = map(int, input().split())
    for j in range(x, x + 10):
        for k in range(y, y + 10):
            if white[j][k] == 0:
                white[j][k] = 1

ans = 0
for i in range(100):
    ans += sum(white[i])

print(ans)