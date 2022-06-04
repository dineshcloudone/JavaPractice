import array as arr
a=arr.array('i',[1,2,3])

print("The new created array is : ", end=" ")
for i in range(0, len(a)):
    print(a[i], end=" ")
print()

b=arr.array('d',[1.1,2.2,3.4])
print("The new floating type array is :", end=" ")
for i in range(0,len(b)):
    print(b[i],end=" ")
    

str=arr.array('s',['din','esh','kumar'])
for i in range(0, len(str)):
    print(str[i], end=" ")
print()


'''
The new created array is :  1 2 3 
The new floating type array is : 1.1 2.2 3.4 Traceback (most recent call last):
  File "C:\Dinesh\Dinesh_dg185171\Dinesh\Java\Eclipse_Projects\Java_Practice\Python_Practice\datatypes\geekfgeek_arrays\array_creation.py", line 16, in <module>
    str=arr.array('s',['din','esh','kumar'])
ValueError: bad typecode (must be b, B, u, h, H, i, I, l, L, q, Q, f or d)

'''