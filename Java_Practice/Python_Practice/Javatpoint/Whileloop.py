'''
Created on Mar 27, 2021

@author: dg185171
'''
def continuestmt():
    i=0
    str1="javatpoint"
    
    while i<len(str1):
        if str1[i]=='a' or str1[i]=='t':
            i+=1
            continue
        print("current letter:",str1[i])
        i+=1

def breakstmt():
    i=0
    str1='javatpoint'
    
    while i<len(str1):
        if str1[i]=='t' :
            i+=1
            break
        print("current letter",str1[i])
        i+=1
        
def passstmt():
    str1='javatpoint'
    i=0
    
    while i<len(str1):
        i+=1
        pass
    
    print("Value of i :",i)
    
    
def fiboncciseries():
    n=10
    a=0
    b=1
    count=0
    while (count < n):  
        print(a, end = ' ')  
        c = a + b  
        # updateing values  
        a = b  
        b = c  
        count+=1
    
    
#continuestmt()

#passstmt()

fiboncciseries()