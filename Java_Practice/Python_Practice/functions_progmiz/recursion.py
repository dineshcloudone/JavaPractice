'''
Created on May 14, 2021

@author: dg185171
'''
'''
def factorial(x):
    
    if x==1:
        return 1
    else:
        
        return(x*factorial(x-1))


num =3
print("factorial value of", num, "is", factorial(num) )
'''

def recur_fibo(n):  
   if n <= 1:  
       return n  
   else:  
       return(recur_fibo(n-1) + recur_fibo(n-2))  
# take input from the user  
nterms = int(input("How many terms? "))  
# check if the number of terms is valid  
if nterms <= 0:  
   print("Plese enter a positive integer")  
else:  
   print("Fibonacci sequence:")  
   for i in range(nterms):  
       print(recur_fibo(i))  