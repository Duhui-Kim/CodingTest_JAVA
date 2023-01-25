N, M = map(int, input().split())
numbers = list(map(int, input().split()))
i_j_list = []

for m in range(M):
    i_j = list(map(int, input().split()))
    i_j_list.append(i_j)

prefix_sum = [0]
numbers_sum = 0

for number in numbers:
    numbers_sum += number
    prefix_sum.append(numbers_sum)

for i_j in i_j_list:
    i, j = i_j[0], i_j[1]
    print(prefix_sum[j] - prefix_sum[i-1])