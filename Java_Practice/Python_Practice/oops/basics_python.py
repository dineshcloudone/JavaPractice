'''
Created on Mar 6, 2018

@author: dg185171
'''

class MyClass:
    "this is javatpoint"
    a=5
    b="dinesh"
    def ifEx1(self):
        a=10
        if a==10:
            print("this is sample if condition")

    def ifElseEx(self):
        a=20
        if a==20:
            print("this is if condition ")
            a=self.a
            
            print(a)
        else:
            print("this is else condition")
            
    def elIfEx(self):
        a=10
        b=20
        c=30
        if a==10:
            print("this is if in elif "+a.__str__())
        elif b==20:
            print("this is elif"+b.__str__())
        else:
            print("this is else condition"+c.__str__())
    
    def forEx1(self):
        for i in range(1,6):
            print(i)
    
    def forEx2(self):
        
        for i in range(1,6):
            for j in range(1,i+1):
                print(i, end=" ")                
            print("\r")
            
            
    def whileEx1(self):
        a=20;
        while a>0:
            print("value of a in this iteration: "+a.__str__())
            a=a-2
    
    def whileEx2(self):
        n=153
        sum=0
        while n>0:
            r=n%10
            sum+=r    
            n=n/10
        print(sum)
        
    def iterateList(self):
        #l1=[1,2,3,4]
        for i in [1,2,3]:
            if i==2:
                print("value in list",i)
                    
                
    def printShape(self):
        print("   /|")    
        print("  / |")
        print(" /  |")
        print("/__ |")
        
    
        
ob=MyClass()
#ob.iterateList()d
#ob.printShape()
ob.whileEx1()

            
            
        