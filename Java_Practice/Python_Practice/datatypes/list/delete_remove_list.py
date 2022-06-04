'''
Created on May 8, 2021

@author: dg185171
'''
my_list=['d','i','n','e','s','h']

del my_list[1]

print(my_list)

del my_list[2:4]

print(my_list)


del my_list

print(my_list)


#remove = to remove given item from the list
#pop = to remove the item at given index, if index is not provided it removes last item
#clear = we can use this method to clear the list

my_list=['d','i','n','e','s','h']

my_list.remove('d')

print(my_list)

print(my_list.pop(1))

print(my_list)

print(my_list.pop())

print(my_list)

my_list.clear()

print(my_list)