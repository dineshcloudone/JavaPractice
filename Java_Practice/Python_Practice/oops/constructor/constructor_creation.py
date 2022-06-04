'''
Created on May 5, 2021

@author: dg185171

--> what is duck typing

'''

class Employee:
    def __init__(self,name,id):
        self.name=name
        self.id=id
        
    def display(self):
        print('ID:%d \n Name:%s' % (self.id,self.name))
        


emp1=Employee('dinesh', 123)
emp1.display()