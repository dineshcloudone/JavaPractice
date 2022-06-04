'''
Created on May 14, 2021

@author: dg185171
'''

class function_argument:
    def greet(self,name,msg):
        print("Hello " + name + ","+msg)
    
    def greet_var(self,name,msg="Good Morning"):
        print("Hello ", name + "," + msg)

    def arb_arguments(self,*names):
        for name in names:
            print("this is ", name)
    
    def arb_arguments_keyValue(self,*names,**age):
        for arg in age:
            print("key is: ", arg, "value is: ",age[arg])
        for name in names:
            print("value is", name)

arg=function_argument()
arg.greet('Dinesh', 'how are you')

arg.greet('Dinesh', 'where are you')
arg.greet_var('dinesh')
arg.arb_arguments("hari","giri","suri")

arg.arb_arguments_keyValue('asfls',age1="14",age2="15")