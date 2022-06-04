'''
Created on May 14, 2021

@author: dg185171
'''


double=lambda x:x**2
print(double(5))

my_list=[1,5,4,6,7,8,10]

new_list=list(filter(lambda x: (x%2==0), my_list))
print(new_list)

