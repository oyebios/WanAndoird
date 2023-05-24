package aaa.lw.blog

class NativeLib {

    /**
     * A native method that is implemented by the 'blog' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'blog' library on application startup.
        init {
            System.loadLibrary("blog")
        }
    }
}