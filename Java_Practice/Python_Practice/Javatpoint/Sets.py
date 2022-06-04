'''
Created on Mar 28, 2021

@author: dg185171
'''
from builtins import set
def curlybraces_set():
    Days={'Monday','Tuesday','wednesday','Thursday','Friday','Saturday','Sunday'}
    print(Days)
    print(type(Days))
    print("looping through the set elements")
    for i in Days:
        print(i)
        
def set_method():
    Days=set(['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'])
    print(Days)
    print(type(Days))
    print('looping through the set elements')
    for i in Days:
        print(i)
        
def duplicateelements():
    set5={1,2,4,4,5,6,7,7}
    print("printing the set with unique elements",set5)
        
def addingelements():
    Months=set(['Januaary','February'])
    Months.add('March')
    Months.add('April')
    Months.add('May')
    for i in Months:
        print(i)
        
    Months.update(['June','July'])
    Months.discard('July')
    Months.discard('July')
    Months.remove('June')
    
    for i in Months:
        print(i)
    
#curlybraces_set()
#set_method()

#duplicateelements()

addingelements()
