'''
Created on May 3, 2021

@author: dg185171

Link : https://www.geeksforgeeks.org/polymorphism-in-python/ 
'''
class Bird:
    def intro(self):
        print("There are many types of birds")
    
    def flight(self):
        print("Most of the birds can fly but some cannot")
        
class sparrow(Bird):
    def flight(self):
        print("sparrows can fly")
    
class ostrich(Bird):
    def flight(self):
        print("Ostriches cannot fly")


obj_bird=Bird()
obj_sparrow=sparrow()
obj_ostrich=ostrich()

for obj in (obj_bird,obj_ostrich,obj_sparrow):    
    obj.intro()
    obj.flight()
    