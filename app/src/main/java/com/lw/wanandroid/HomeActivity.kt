package com.lw.wanandroid

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import androidx.activity.viewModels
import com.lw.mvvm.baseui.BaseActiviy
import com.lw.mvvm.navigation.initAndroidxNavigationContorller
import com.lw.wanandroid.data.UserInfo
import com.lw.wanandroid.databinding.ActivityMainBinding
import com.lw.wanandroid.viewmodel.HomeViewmodel


class HomeActivity : BaseActiviy<ActivityMainBinding>() {

    val homeViewmodel by viewModels<HomeViewmodel>()
    override fun initView(savedInstanceState: Bundle?) {
        //初始化AndroidX navigation导航
        initAndroidxNavigationContorller(supportFragmentManager,R.id.id_fragment_container,findViewById(R.id.id_bottom_navigation))
        val user = UserInfo()
        activityBinding?.user = user
        user.user.set("初始化")

//        FrameTrace().addHandlerLogger()
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    var nfcAdapter: NfcAdapter? = null
    fun readNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            println("nfc not support")
        } else if (!nfcAdapter!!.isEnabled()) {
            println("nfc unabled")
        } else {
            println("nfc ok")
        }
    }

    override fun onResume() {
        super.onResume()
        readNFC()
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        )
        val intentFilters = arrayOf<IntentFilter>()
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilters, null)

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        println("lai l ")
        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            if (tag?.id != null && !tag.id.isEmpty()) {
                val hexString = tag.id.joinToString("") { "%02x".format(it) }
                println(hexString)
            }
        }
    }
}