package aaa.lw.blog.main.base

interface ILog {
    fun d(tag: String, msg: String)
    fun e(tag: String, msg: String)
    fun e(tag: String, msg: String, throwable: Throwable)
    fun flow(tag: String, method: String, msg: String)
}