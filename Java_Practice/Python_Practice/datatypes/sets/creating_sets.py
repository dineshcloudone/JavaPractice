
#set is unordered collection of items
# A set cannot have mutable elements like list, sets or dictionaries
#Add, update(can add more elements) -> to add new elements
#discard , remove(will throw an error if element not found), pop(removes random element), clear
#Set Operations : A.union(B), A.intersection(B), A.difference(B), A.symmetricDifference(B)
#While tuples are immutable lists, frozensets are immutable sets.

#set of integers
my_set={1,2,3}

print(my_set)

#set of mixed data types
my_set={1.0,'hello',(1,2,3)}
print(my_set)

#set cannot have duplicate values
my_set={1,2,2,3,4}
print(my_set)

my_set=set([1,2,2,3,4])
print(my_set)

#set cannot have mutable items
my_set={1,2,[3,4]}

#creating an empty set is tricky

my_set={}
print(my_set)
my_set=set()
print(my_set)