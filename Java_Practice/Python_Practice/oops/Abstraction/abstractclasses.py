'''
Created on May 3, 2021

@author: dg185171

Points to Remember :
--> An Abstract class can contain the both method normal and abstract method.
--> An Abstract cannot be instantiated; we cannot create objects for the abstract class.

links :
https://www.javatpoint.com/abstraction-in-python 
https://www.askpython.com/python/oops/abstraction-in-python

Mutability concept :
https://www.askpython.com/python/oops/immutable-objects-in-python

closures :
https://www.askpython.com/python/oops/closures-in-python

decorators :
https://www.programiz.com/python-programming/decorator

python *args and **kwargs
https://www.programiz.com/python-programming/args-and-kwargs#:~:text=*args%20and%20**kwargs%20are,to%20take%20variable%20length%20argument.&text=**kwargs%20passes%20variable%20number,kwargs%20make%20the%20function%20flexible.

'''

def varargs(*a,**kwargsb):
    print("*" * 30)
    for i in a:
        print(i)

z={1,2,3,4}
varargs(z)