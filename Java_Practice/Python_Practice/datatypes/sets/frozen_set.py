'''
Created on May 9, 2021

@author: dg185171
'''
# Frozensets
# initialize A and B
A = frozenset([1, 2, 3, 4])
B = frozenset([3, 4, 5, 6])


print(A.isdisjoint(B))
#False
print(A.difference(B))
#frozenset({1, 2})
print( A | B)
frozenset({1, 2, 3, 4, 5, 6})
print(A.add(3))
