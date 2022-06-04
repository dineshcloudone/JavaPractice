'''
Created on May 10, 2021

link : https://www.geeksforgeeks.org/enumerate-in-python/?ref=lbp

@author: dg185171
'''
l1=['work','eat','sleep','repeat']
s1='geek'

obj1=enumerate(l1)
obj2=enumerate(s1)

print("Return type: ",type(obj1))
print(list(enumerate(l1)))

print(list(enumerate(s1,2)))

#python program to illustrate enumerate function in loops
l2=['work','eat','sleep','repeat']

for item in enumerate(l2):
    print(item)
print()

for count,item in enumerate(l2,100):
    print(count,item)

