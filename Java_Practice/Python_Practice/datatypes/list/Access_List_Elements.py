'''
Created on May 8, 2021

@author: dg185171
'''
#List indexing

#IndexError, TypeError

my_list=['p','r','o','b','e']

#positive indexing
print(my_list[0])
print(my_list[1])
print(my_list[4])

print("nested index")
n_list=["Happy",[2,0,1,5]]
print(n_list[1][2])

#negative indexing
print("negative index")
print(my_list[-1])
print(my_list[-3])


#iterating a list
lis_names=['dinesh','kumar','gurram']

for i in lis_names:
    print(i, end=" ")

