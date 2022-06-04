

'''
Created on Mar 27, 2021

@author: dg185171
'''

# all kinds of pyramids : https://www.javatpoint.com/how-to-print-pattern-in-python 

def strprint():
    str="python"
    for a in str:
        print(a)
        
def listprinttable():
    list=[1,2,3,4,5,6,7,8,9,10]
    n=5
    for i in list:
        print(i," * 5 =", n*i)

def listprintsum():
    list=[10,30,40,50]
    sum=0
    for i in list:
        sum=sum+i
    print("sum is",sum)

# range(start,stop,step size)
def forlooprange():
    for i in range(10):
        print(i,end=' ')
        
def rangefullsyntax():
    n=int(input("enter number"))
    for i in range(2,n,2):
        print(i)

def rangeList():
    list=["kdf","kdfjasd","dsfls","skjfjdslk"]
    for i in range(len(list)):
        print("hello ", list[i])

def astrictpyramid():
    rows=int(input("enter number of rows"))
    for i in range(0,rows+1):
        for j in range(i):
            print("*",end=' ')
        print()
        
def numberpyramid():
    rows=int(input("enter number of rows"))
    for i in range(1,rows+1):
        for j in range(1,i+1):
            print(j,end=' ')
        print()
    
def pyramidwithspace():
    rows = int(input("Enter the number of rows:"))  
    k = 2 * rows - 2  # It is used for number of spaces  
    for i in range(0, rows):  
        for j in range(0, k):  
            print(end=" ")  
        k = k - 2   # decrement k value after each iteration  
        for j in range(0, i + 1):  
            print("* ", end="")  # printing star  
        print("")

#strprint()
#listprinttable()

#listprintsum()
#forlooprange()

#rangefullsyntax()

#rangeList()

#astrictpyramid()

#numberpyramid()

pyramidwithspace()