'''
Created on Mar 28, 2021

@author: dg185171
'''
from test.test_typing import Employee
from builtins import dict

def creatingdict():
    Employee={"Name":"Age","Age":29,"Salary":25000,"Company":"GOOGLE"}
    print(type(Employee))
    print("printig employee data", Employee)
    
    
def access_dict():
    Employee=dict({"Name":"john","Age":29,"Salary":25000,"Company":"GOOGlE"})
    print("printing the dictionary values")
    print("Name of employee",Employee["Name"])
    print("Keys : ",Employee.keys())
    Employee["Name"]="sam"
    print("Name of employee :",Employee["Name"])
    Employee.__reversed__()
    print(Employee)
#link to delte dictinary elements : https://www.programiz.com/python-programming/dictionary#:~:text=Removing%20elements%20from%20Dictionary&text=The%20popitem()%20method%20can,or%20the%20entire%20dictionary%20itself. 
    
def printdictionary():
    dict={"Name":"Tom", "Age":22}

    for i in dict:
        print(i,":",dict.get(i))
        
#printdictionary()

access_dict()