package com.lw.library

import com.lw.library.filecert.sha256
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testFilesha256() {
        val sha256 =
            sha256("F:\\lwd\\project\\WanAndroid\\app\\build\\outputs\\apk\\release\\app-release.apk")
        println(sha256)
    }
}