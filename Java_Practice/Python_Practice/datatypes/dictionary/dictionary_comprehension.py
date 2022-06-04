'''
Created on May 9, 2021

@author: dg185171
'''
squares = {x: x*x for x in range(6)}

print(squares)



# Dictionary Comprehension with if conditional
odd_squares = {x: x*x for x in range(11) if x % 2 == 1}

print(odd_squares)