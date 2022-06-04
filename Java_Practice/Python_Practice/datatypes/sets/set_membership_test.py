'''
Created on May 9, 2021

@author: dg185171
'''
# in keyword in a set
# initialize my_set
my_set = set("apple")

# check if 'a' is present
# Output: True
print('a' in my_set)

# check if 'p' is present
# Output: False
print('p' not in my_set)


#iterating through set
for letter in set("apple"):
    print(letter)