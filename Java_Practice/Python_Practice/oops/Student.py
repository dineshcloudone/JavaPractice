'''
Created on Apr 24, 2018

@author: dg185171
'''
class Student:
    def __init__(self,id,name):
        self.id=id
        self.name=name
    def displayStudent(self):
        print("student details ",self.id, self.name)

ob=Student(1,"dinesh")
ob.displayStudent()
        