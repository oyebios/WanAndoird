package com.lw.bapm

class BAPM {

    object Inner {
        val BAMP = BAPM()
    }

    companion object {
        @JvmStatic
        fun getInstance() = Inner.BAMP
    }


    /**
     * 电池
     */

}