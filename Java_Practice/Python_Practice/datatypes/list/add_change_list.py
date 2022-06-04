'''
Created on May 8, 2021

@author: dg185171
'''
#Link : https://www.programiz.com/python-programming/list

#methods used to add elements : append, extend, insert
#remove : removes the given values
#clear : clears all elements from the list
#pop : removes the item from the given index, if we don't give index it removes the last item and prints it
#removing elements by assigning empty list
#methods : index(), count(), sort(), reverse()
#list membership is there
#list comprehension 
#Iterating through List

add=[2,4,6,8]

print(2 in add)

add[0]=1

print(add)

add[1:4]=[3,5,7]

print(add)

add.append(8)

print(add)

add.extend([9,10])
add.insert(0, 123)

print(add)

#concatenate (+)

a=[1,2]
b=[3,4]
c=a+b
print(c)
print(c+[5,6])

#multiply
print([1]*3)

print(['dinesh']*2)

z=1 in a
print(z) 






