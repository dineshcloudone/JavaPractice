'''
Created on Mar 7, 2018

@author: dg185171
'''
class ComplexNumber:
    def __init__(self,r=0,i=0):
        self.real=r
        self.image=i
    
    def getData(self):
        print("{0}+{1}j".format(self.real, self.image))
        
'''c1=ComplexNumber(11,12)
c1.getData()'''

c2=ComplexNumber(11)
c2.attr=12
print(c2.real,c2.image,c2.attr)



