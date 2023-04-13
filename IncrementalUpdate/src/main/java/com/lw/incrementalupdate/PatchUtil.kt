package com.lw.incrementalupdate

class PatchUtil {

    companion object {
        init {
            System.loadLibrary("incrementalupdate")
        }
    }


    /**
     * 合并APK文件
     * @param oldApkFile 旧APK文件路径
     * @param newApkFile 新APK文件路径（存储生成的APK的路径）
     * @param patchFile 差异文件
     */
    external fun patchAPK(oldApkFile: String?, newApkFile: String?, patchFile: String?)
}