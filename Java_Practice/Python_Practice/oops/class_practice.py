'''
Created on Mar 7, 2018

@author: dg185171
'''

class Student:
    def __init__(self,id,name):
        self.id=id
        self.name=name
    def displayStudent(self):
        #display funtion
        print("id :",self.id,",name :",self.name)
        print(__doc__);
emp1=Student(1,'dinesh') 
emp2=Student(2,'kumar')
emp1.displayStudent()
emp2.displayStudent()           
        
        
    
