import re

with open("./wordlist/english3.txt") as f:
    words = f.readlines()

print(len(words))

pattern = re.compile(r"^[a-zA-Z]+$")

count = 0
for word in words:
    stripped = word.strip()
    word_len = len(stripped)
    if pattern.match(stripped):
        with open(f"./wordlist/{word_len}.txt", "a") as f:
            f.write(f"{stripped}\n")
        count += 1

print(count)
