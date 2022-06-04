'''
Created on May 9, 2021

@author: dg185171
'''
#union(|), intersection(&), difference(-), symmetric-difference(^)

# Set union method
# initialize A and B
A = {1, 2, 3, 4, 5}
B = {4, 5, 6, 7, 8}

# use | operator
# Output: {1, 2, 3, 4, 5, 6, 7, 8}
print(A | B)

# use union function
print(A.union(B)) 
{1, 2, 3, 4, 5, 6, 7, 8}

# use union function on B
print(B.union(A))
{1, 2, 3, 4, 5, 6, 7, 8}




# Intersection of sets
# initialize A and B
A = {1, 2, 3, 4, 5}
B = {4, 5, 6, 7, 8}

# use & operator
# Output: {4, 5}
print(A & B)



#using intersection function on A
print(A.intersection(B))

print(B.intersection(A))




# Difference of two sets
# initialize A and B
A = {1, 2, 3, 4, 5}
B = {4, 5, 6, 7, 8}

# use - operator on A
# Output: {1, 2, 3}
print(A - B)



# use difference function on A
print(A.difference(B))

# use - operator on B
print(B - A)

# use difference function on B
print(B.difference(A))





# Symmetric difference of two sets
# initialize A and B
A = {1, 2, 3, 4, 5}
B = {4, 5, 6, 7, 8}

# use ^ operator
# Output: {1, 2, 3, 6, 7, 8}
print(A ^ B)

print(A.symmetric_difference(B))
print(B.symmetric_difference(A))