'''
Created on May 5, 2021

@author: dg185171

'''
class parent:
    
    __default="parent"
    
    def __init__(self, a=None):
        self.b=a or self.__default
        print(self.b)
        

class child(parent):
    __default="child"
    
    
ch_a=child()

ch_a._child__default
#ch_a._Parent__default


'''
class parent(object):
    __default = "parent"
    def __init__(self, name=None):
        self.default = name or self.__default

    @property
    def default(self):
        return self.__default

    @default.setter
    def default(self, value):
        self.__default = value


class child(parent):
    __default = "child"
    
child_a = child()
print(child_a.default)            # 'parent'
print(child_a._child__default)    # 'child'
print(child_a._parent__default)   # 'parent'

child_b = child("orphan")
## this will show 
print(child_b.default)            # 'orphan'
print(child_a._child__default)    # 'child'
print(child_a._parent__default)   # 'orphan'

'''