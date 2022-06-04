'''
Created on May 8, 2021

@author: dg185171
'''

my_tuple=('d','i','n','e','s','h')

print(my_tuple.count('d'))
print(my_tuple.index('n'))


fruit=('a','p','p','l','e')

print('a' in fruit)

print('d' in fruit)

print('z' not in fruit)

#iterating through a tuple

for i in fruit:
    print(i, end=" ")
    

print('=======================')
tuple1 = (10, 20, 30, 40, 50, 60)    
print(tuple1)    
count = 0    
for i in tuple1:    
    print("tuple1[%d] = %d" %(count, i))   
    count = count+1  
