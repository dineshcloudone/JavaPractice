'''
Created on Apr 26, 2018

@author: dg185171
'''
import os

class ListPractice:
    def readFile(self):
        ob=open("C:\\Users\\dg185171\\Desktop\\1.txt","r+")
        ob.write("working on python files \n")
        print(ob.read())
    

if __name__ == '__main__':
    lp=ListPractice()
    lp.readFile()