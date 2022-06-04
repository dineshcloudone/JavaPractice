'''
Created on Mar 27, 2021

@author: dg185171
'''

class food:
    '''
    classdocs
    '''

    def __init__(self, fruit,color):
        '''
        Constructor
        '''
        self.fruit=fruit
        self.color=color
        
        
    def show(self):
        print("fruit is", self.fruit)
        print("color is", self.color)
        
        
    
apple=food("apple","red")
grapes=food("grapes","green")

apple.show()
grapes.show()
    
    