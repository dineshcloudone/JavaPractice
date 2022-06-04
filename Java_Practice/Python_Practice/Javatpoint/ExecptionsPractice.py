'''
Created on Apr 1, 2021

@author: dg185171
'''

def zerodivisionerror():
    a=1
    b=0
    
    print("a divided by b :",a/b)
    print("Hi i am other part of the program")

def zerodivisionerror_handled():
    try:
        a=1
        b=0
        c=a/b
    
    except Exception as e:
        print("can't divide with zero")
        print(e)

def zerodivisionerror_else():
    
    try:
        a=4
        b=2
        print("a divided by b", a/b)
    except Exception as e:
        print("division error occured")
    else:
        print("without exception program completed")
    
def multiple_excepitons():
    try:
        a=10/0
    except(ArithmeticError,IOError):
        print("Arithmetic exception")
    else:
        print("Successfully Done")
        
        
def finally_stmt():
    try:
        a=3/3
        try:
            print("inner try block")
            
        finally:
            
            print("file closed")
    except:
        print("Error")

def raise_exception():
    try:
        a=int(input("Enter age"))
        if(a<18):
            raise ValueError
        else:
            print("age is valid")
    except ValueError:
        print("Age is not valid")
        
#szerodivisionerror_handled()

#zerodivisionerror_else()

#multiple_excepitons()

#finally_stmt()

raise_exception()