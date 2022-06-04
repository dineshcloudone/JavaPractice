'''
Created on May 5, 2021

@author: dg185171

https://www.programiz.com/python-programming/object-oriented-programming

__init__ : initializer method
class attributes
instance attributes

'''
class Parrot:
    species="bird"
    
    def __init__(self,name,age):
        self.name=name
        self.age=age
        
blu=Parrot("blu",10)
woo=Parrot("woo",15)


# accessing the class attributes

print("Blu is {}".format(blu.__class__.species))
print("woo is {}".format(woo.__class__.species))

# access the instance attributes

print("{} is years {} old".format(blu.name,blu.age))

print("{} is years {} old".format(woo.name,woo.age))

print(Parrot.__name__)
print(Parrot.__class__)

print(blu.species)