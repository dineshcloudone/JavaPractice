'''
Created on May 14, 2021

@author: dg185171
'''
class functions:
    def greet(self,name):
        '''
        this functions sample functions
        '''
        print('Hi '+name+', good morning..!!')
        
        
    def return_Stmt(self,num):
        if num>=0:
            return num
        else:
            return -num
        
    def scope_variable(self):
        x=10
        print("value of x inside function : {}".format(x))
        
        
function_obj=functions()
function_obj.greet("dinesh")
print(functions.greet.__doc__)

print(function_obj.return_Stmt(6))
print(function_obj.return_Stmt(-6))

x=20
function_obj.scope_variable();

print("value of x inside function : {}".format(x))

