package com.lw.kotlin.keyword


open class Person(val name: String)
class Student(name: String) : Person(name) {
    open val age = 11
}

class StrList<T>(val pp: T) {

    fun insert(man: T) {
        println(man.toString())
    }

    fun getout(): T {
        return pp
    }
}


fun main() {
    //协变，帮助子类转为父类，但是对泛型类型(此时是父类)操作只能读取不能写入，因为父类泛型不包含子类泛型额外属性，只能读取父类
    //逆变，帮助父类转为子类，但是对泛型类型(子类)操作只能写入不能读取，因为父类泛型不包含子类泛型额外属性，只能写入子类
    val str: StrList<out Person> = StrList(Student("s"))
    val str2: StrList<in Student> = StrList(Person("p"))
    str2.insert(Student("s2"))
}