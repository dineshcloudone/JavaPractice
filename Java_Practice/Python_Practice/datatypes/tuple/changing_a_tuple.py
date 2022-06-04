'''
Created on May 8, 2021

@author: dg185171
'''


my_tuple=(4,2,3,[6,5])

#tuple doesn't support item assignment
#my_tuple[1]=9

#tuple allows to change the value of mutable element
my_tuple[3][0]=9
print(my_tuple)

#tuple can be re-assigned
my_tuple=('p','r','o','g','r','a','m','i','z')

print(my_tuple)

#concatenation operator : +
tuple1=(1,2,3)
tuple2=(4,5,6)
tuple3=tuple1+tuple2
print(tuple3)


#repeat operator
print(tuple1*2)
