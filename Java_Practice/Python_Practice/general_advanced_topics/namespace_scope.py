'''
Created on May 10, 2021

@author: dg185171

Link :https://www.geeksforgeeks.org/namespaces-and-scope-in-python/?ref=lbp

'''

# var1 is in the global namespace
var1 = 5
def some_func():
 
    # var2 is in the local namespace
    var2 = 6
    def some_inner_func():
 
        # var3 is in the nested local
        # namespace
        var3 = 7
        
        

# Python program processing
# global variable
 
count = 5
def some_method():
    global count
    count = count + 1
    print(count)
some_method()



# Python program showing
# a scope of object
 
def some_func2():
    print("Inside some_func")
    def some_inner_func():
        var = 10
        print("Inside inner function, value of var:",var)
    some_inner_func()
    print("Try printing var from outer function: ",var)
some_func2()