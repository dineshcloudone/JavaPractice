'''
Created on May 3, 2021

@author: dg185171

Link : https://www.geeksforgeeks.org/polymorphism-in-python/ 
'''
from oops.Polymormiphism.polymorphism_inheritance import sparrow
class Bird:
    def intro(self):
        print("There are many types of birds")
        
    def flight(self):
        print("Most of the birds can fly but some cannot")
        
class sparrow:
    def flight(self):
        print("Sparrow can fly")
        
class ostrich:
    def flight(self):
        print("Ostrich cannot fly")
        

def func(obj):
    
    obj.flight()
    
obj_bird=Bird()
obj_sparrow=sparrow()
obj_ostrich=ostrich()

func(obj_bird)
func(obj_sparrow)
func(obj_ostrich)
