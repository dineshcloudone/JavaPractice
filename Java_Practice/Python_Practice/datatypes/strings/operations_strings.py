'''
Created on May 9, 2021

@author: dg185171
'''
str1='dinesh'
str2='kumar'
print(str1+str2)

print(str1*3)

print('dinesh''gurram')

str3=('dinesh'
      'gurram')

print(str3)

#iterating through a string
count=0
for i in 'dinesh':    
    if i=='n':
        count+=1
print(count,'letters found')


#string membership
print('#string membership')
str4='test'
print('e' in str4)

print('z' not in str4)


#built in functions
print('#built in functions')

str5='cold'
print('enumeration of str5',enumerate(str5))
list_enumerate=list(enumerate(str5))
print('list(enumerate(str)) ', list_enumerate)

print('len(str)', len(str5))


