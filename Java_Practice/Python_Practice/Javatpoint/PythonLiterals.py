'''
Created on Mar 27, 2021

@author: dg185171
'''
class stringliterals:
    def singlelinestring(self):
        text1="hello"
        print(text1)
        
    def backslasheofline(self):
        text1='hello\
        user'
        print(text1)       

    def triplequotation(self):
        text1='''welcome 
            to python course'''
        print(text1)

stringInstance=stringliterals()

'''
stringInstance.singlelinestring()
stringInstance.backslasheofline()
stringInstance.triplequotation()
'''
class numericliterals:
    def numericlit(self):
        x = 0b10100 #Binary Literals  
        y = 100 #Decimal Literal   
        z = 0o215 #Octal Literal  
        u = 0x12d #Hexadecimal Literal  
          
        #Float Literal  
        float_1 = 100.5   
        float_2 = 1.5e2  
          
        #Complex Literal   
        a = 5+3.14j  
          
        print(x, y, z, u)  
        print(float_1, float_2)  
        print(a, a.imag, a.real)
        
        

