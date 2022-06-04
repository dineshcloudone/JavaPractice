'''
Created on Apr 25, 2018

@author: dg185171
'''

class StringsPractice:
    def lenEx(self):
        name='dinesh'
        print("no of chars in name:",len(name))
        i=0
        for n in range(-1,(-len(name)-1),-1):
            print(name[i],"\t",name[n])
            i=i+1
            
    def stringEx2(self):
        print(5*"dinesh")
        
    def sortAlphabets(self,*alphabets):
        list=[]
        for i in alphabets:
            list.append(i)
            list.sort(key=None, reverse=False)    
        print(list)
        
        
'''
Python String operators
1.Basic Operators
2.Membership Operators
3.Relational Operators
4.Python String Slice Notation

1. Basic Operators : + and *
'''



if __name__ == '__main__':
    ob=StringsPractice()
    #ob.stringEx2()
    ob.sortAlphabets('z','y','c','b','a')
    
