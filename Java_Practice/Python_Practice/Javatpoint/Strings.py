'''
Created on Mar 27, 2021

@author: dg185171
'''
from Tools.scripts.md5sum import printsum

class stringhandling:

    def stringEx(self):


        str="String using dou7ble quotes"
        
        print(str)
        s='''A multiline 
        string'''
        
        print(s)
        
    def stringIndex(self):
        str1= 'hello javatpoing'
        str2= 'how are you'
        print(str1[3:])
        print(str1[0])
        print(str1*2)
        print(str1+str2)
        
    def List(self):
        list1=[1,"hi","python",2]
        print(type(list1))
        print(list1)
        print(list1[3:])
        print(list1[0:3])
        print(list1+list1)
        print(list1*2)
        list1[2]="python3"
        print(list1)

#tuple is same as List but in type paranthesis are used and you can't modify it

    def dictionary(self):
        d={1:'a',2:"b",3:"c"}
        print(d)
        print("1st name is"+d[1])
        print("1st name is"+d[3])
        d.update({4:"d"})
        
        print(d.keys())
        print(d.values())
        
        
    def boolean(self):
        print(type(True))
        print(type(False))
        
    def printsubstring(self):
        str='hello'
        print(str[:])
        print(str[1:])
        print(str[:4])
        print(str[3:4])
        
    def printsubstringrevorder(self):
        str='hello'
        
        print(str[-4:-1])
        
# set is also same as like dictionary but single vaules it holds
inst1=stringhandling()

#inst1.stringEx()

#inst1.dictionary()
#inst1.boolean()
#inst1.printsubstring()
inst1.printsubstringrevorder()
        
        