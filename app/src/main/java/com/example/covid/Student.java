package com.example.covid;

//TODO: implement this class to store local student data more efficiently
public class Student {
    //public int TimeCompleted;
    public int symptoms;
    public String name;
    public Student(String name, int symptoms){
        this.name = name;
        this.symptoms = symptoms;
    }
    public Student(){}
}
