'''
Created on May 3, 2021

@author: dg185171

Link : https://www.geeksforgeeks.org/polymorphism-in-python/ 
'''
class India:
    def capital(self):
        print("New Delhi is the capital of india")
        
    def language(self):
        print("Hindi is the most widely used language of india")
        
class USA:
    def capital(self):
        print("washington, D.C is the capital of USA")
        
    def language(self):
        print("English is the primary language of USA")
        
obj_ind=India()
obj_usa=USA()

for country in (obj_ind,obj_usa):
    country.capital()
    country.language()