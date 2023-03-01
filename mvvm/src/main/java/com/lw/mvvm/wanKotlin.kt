package com.lw.mvvm


class OtherDatabse(base: MyDataBase) :
    UserDao by base.getUserDao(),
    CarDao by base.getCarDao()


abstract class MyDataBase {
    abstract fun getUserDao(): UserDao
    abstract fun getCarDao(): CarDao

}


interface CarDao {
    fun getCar()
    fun delCar()
}

interface UserDao {
    fun getUser()
    fun delUser()
}