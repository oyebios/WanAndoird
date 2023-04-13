package com.lw.library.filecert

import java.io.FileInputStream
import java.security.MessageDigest


/**
 * 计算文件的sha256值，用于校验文件完整性
 */
fun sha256(filePath: String): String {
    try {
        val md = MessageDigest.getInstance("SHA-256")
        val fis = FileInputStream(filePath)
        val dataBytes = ByteArray(1024)
        var bytesRead: Int
        while (fis.read(dataBytes, 0, 1024).also { bytesRead = it } != -1) {
            md.update(dataBytes, 0, bytesRead)
        }
        val hashBytes = md.digest()
        val sb = StringBuilder()
        for (b in hashBytes) {
            sb.append(String.format("%02x", b))
        }
        fis.close()
        return sb.toString()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return "sha256 error !!"
}