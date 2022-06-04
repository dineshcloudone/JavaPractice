'''
Created on Mar 27, 2021

@author: dg185171
'''
def assert_keyword():
    a=10
    b=0
    print("a is dividing by zero")
    assert b!=0
    print(a/b)
    
def def_keyword(a,b):
    c=a+b
    print(c)
    
def continue_keyword():
    a=0
    while a<4:
        a+=1
        if a==2:
            continue
        print(a)
        
def for_keyword():
    for i in range(5):
        print(i)
        if i==2:
            break
    print("End of execution in for loop")
    
def if_keyword():
    i=18
    if(i<20):
        print("i am less than 18")
     
def if_else_keyword():
    a=20
    if(a/20):
        print("even")
    else:
        print("odd")
    
def if_elif_keyword():
    marks=int(input("Enter the marks:"))
    if(marks>=90):
        print("Excellent")
    elif(marks<90 and marks>75):
        print("verygood")
    elif(marks<75 and marks>60):
        print("Good")
    else:
        print("average")
        
def del_keyword():
    a=10
    b=20
    print(a)
    del b
    print(b)

def nonlocal_keyword():
    a = 20   
    def inside_function():  
        nonlocal a  
        a = 30  
        print("Inner function: ",a)  
    inside_function()  
    print("Outer function: ",a)
    
def lambda_keyword():
    a=lambda x:x**2
    for i in range(1,6):
        print(a(i))  
       
    
#assert_keyword()
#def_keyword(10,20,30)
#continue_keyword()
#for_keyword()
#if_keyword()
#nonlocal_keyword()
lambda_keyword()


