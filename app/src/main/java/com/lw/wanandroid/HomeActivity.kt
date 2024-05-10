package com.lw.wanandroid

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord.createTextRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Bundle
import androidx.activity.viewModels
import com.lw.mvvm.baseui.BaseActiviy
import com.lw.mvvm.navigation.initAndroidxNavigationContorller
import com.lw.wanandroid.data.UserInfo
import com.lw.wanandroid.databinding.ActivityMainBinding
import com.lw.wanandroid.viewmodel.HomeViewmodel
import java.nio.charset.StandardCharsets


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
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            println("lai 2 ")
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            if (tag?.id != null && !tag.id.isEmpty()) {
                tag.techList.forEach {
                    println(it)
                }
                if (tag.techList.contains("android.nfc.tech.NdefFormatable")) {
                    var ndefFormatable = NdefFormatable.get(tag)
                    ndefFormatable.connect()
                    ndefFormatable.format(NdefMessage(createTextRecord(null, "A4:C1:38:6B:82:AA")))
                    ndefFormatable.close()
                } else {
                    val ndef = Ndef.get(tag)
                    ndef.connect()
//                    ndef.writeNdefMessage(NdefMessage(createTextRecord(null,"1002")))
//                val createTextRecord = NdefRecord.createTextRecord(null, "CC:2B:D8:7C:32:0E")
//                val payload = createTextRecord.payload
//                val langeuageSize = payload[0].toInt()
//                println(langeuageSize)
//                val copyOfRange = payload.copyOfRange(1+langeuageSize, payload.size)
//                println(payload.toString(StandardCharsets.UTF_8))
//                println(copyOfRange.toString(StandardCharsets.UTF_8))
//                ndef.writeNdefMessage(NdefMessage(createTextRecord))
                    ndef.ndefMessage.records.forEach {
                        val payload = it.payload
                        val langeuageSize = payload[0].toInt()
                        println(langeuageSize)
                        val copyOfRange = payload.copyOfRange(1 + langeuageSize, payload.size)
                        println(payload.toString(StandardCharsets.UTF_8))
                        println(copyOfRange.toString(StandardCharsets.UTF_8))
                    }
                    ndef.close()
                }

            }
        }
    }

    override fun onPause() {
        super.onPause()
        startActivity(Intent(this, javaClass))
    }
}