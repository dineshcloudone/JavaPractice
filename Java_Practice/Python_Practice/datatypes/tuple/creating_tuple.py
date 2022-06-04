'''
Created on May 8, 2021

@author: dg185171
'''


#you cannot change the value of tuple as it is immutable, 
#however you can change the value of mutable element in the tuple Ex below
'''
# Changing tuple values

# However, item of mutable element can be changed
my_tuple[3][0] = 9    # Output: (4, 2, 3, [9, 5])
print(my_tuple)
'''

my_tuple=()
print(my_tuple)

my_tuple=(1,2,3)
print(my_tuple)

my_tuple=(1,"Hello",2,3)
print(my_tuple)

my_tuple=("mouse",[8,4,6],(1,2,3))
print(my_tuple)


#a tuple can also be created without parenthesis
my_tuple=3,4.6,"dog"
print(my_tuple)


#tuple unpacking
print("tuple unpacking")
a,b,c=my_tuple

print(a)
print(b)
print(c)

#creating a tuple is bit tricky
my_tuple=("hello")
print(type(my_tuple))

my_tuple=("hello",)
print(type(my_tuple))

my_tuple="hello",
print(type(my_tuple))
print(my_tuple.__len__())

