'''
Created on May 5, 2021

@author: dg185171
'''
class Employee:
    count=0
    
    def __init__(self):
        Employee.count=Employee.count+1


emp1=Employee()
emp2=Employee()
emp3=Employee()

print("objects count : ", Employee.count)