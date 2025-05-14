with open('heavy.txt','w') as F:
    for i in range(10000):
        F.write(f"{i}\n")