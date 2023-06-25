package com.lw.wanandroid.fragment

import aaa.lw.bluetooth.BlueToothUtils
import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lw.mvvm.baseui.BaseFragment
import com.lw.wanandroid.R
import com.lw.wanandroid.ScrollingActivity
import com.lw.wanandroid.data.UserInfo
import com.lw.wanandroid.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    var adapter: BasicAdapter? = null
    override fun initView(mRootLayout: View?) {
        val user = UserInfo()
        fragmentBinding?.idHomeBtn1?.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_page2)
//            user.user.set("第二次")
//
////            BStartUp.getInstance().startUpWithXml(activity)
//            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//            val myRef: DatabaseReference = database.getReference("message/info")
//
//            myRef.setValue("Hello, World!")
//            bluetoothFun()
            activity!!.startActivity(Intent(activity, ScrollingActivity::class.java))

        }
        fragmentBinding?.user = user
        fragmentBinding?.userInput = user
        fragmentBinding?.idRcv?.layoutManager = LinearLayoutManager(context)
        adapter = BasicAdapter(blueToothDevices)
        fragmentBinding?.idRcv?.adapter = adapter
        user.user.set("初始化")


//        GlobalScope.launch {
//            val userDb =
//                createDB(activity!!.applicationContext!!, AppDatabase::class.java, "user_info_base")
//            userDb.userDao()
//                .insertAll(User(user = user.user.get(), password = user.pwd.get(), uid = 1))
//            val all = userDb.userDao().getAll()
//            all.forEach {
//                Log.e("testroom", it.user + "")
//            }
//        }

    }

    val blueToothDevices: MutableList<String> = arrayListOf()
    private fun bluetoothFun() {


        val blueToothUtils = BlueToothUtils()
        blueToothUtils.isDeviceSupportBlueTooth(activity as Context)
        val blueToothEnable = blueToothUtils.isBlueToothEnable()
        fragmentBinding?.user?.user?.set(
            if (blueToothEnable) {
                "开了"
            } else "没开"
        )

        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        activity?.registerReceiver(receiver, filter)
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("permissiont denined")
            requestPermissions(
                arrayOf(Manifest.permission.BLUETOOTH_SCAN),
                1001
            );
            return
        }
        println("蓝牙是否在使用中${blueToothUtils.bluetoothAdapter?.isDiscovering}")
        val startDiscovery = blueToothUtils.bluetoothAdapter?.startDiscovery()
        println("start discovery $startDiscovery")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1001) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start scanning for Bluetooth devices
                bluetoothFun()
            } else {
                println("permissiont denined2")
            }
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver = object : BroadcastReceiver() {

        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name
                    val deviceHardwareAddress = device?.address // MAC address


                    if (deviceName != null) {
//                        println("设备名称:$deviceName,设备mac:$deviceHardwareAddress")
                        blueToothDevices.add("设备名称:$deviceName,设备mac:$deviceHardwareAddress")
                        adapter?.notifyDataSetChanged()

                        if (deviceName == "TAT-2000-BT") {
                            device.connectGatt(context, false, gattCallback)
                        }
                    }
                }
            }
        }
    }
    private val gattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // Device connected, start discovering services
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // Device disconnected
            }
        }

        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            // Services discovered, do something with them
            gatt.services.forEach {
                println("services ${it.uuid.toString()}")
                if (it.uuid.toString() == "91bfccdd-5480-4f59-f86e-2862d1b31008") {
                    it.characteristics.forEach {
                        if (it.uuid.toString().contains("7dd5")) {
                            gatt.readCharacteristic(it)
                        }
                    }
                }
            }
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            characteristic?.service?.uuid
            println("service ${characteristic?.service?.uuid}   characteristic ${characteristic?.uuid.toString()}")
            val data = characteristic!!.value
            val stringBuilder = StringBuilder()
            val stringBuilderTemp = StringBuilder()
            data.forEach {
                stringBuilder.append(Integer.toHexString(it.toInt()))
                stringBuilder.append('-')
            }
            println(stringBuilder.toString())
            stringBuilderTemp.append(Integer.toHexString(data[4].toInt()))
            stringBuilderTemp.append(Integer.toHexString(data[5].toInt()))
            val temp = Integer.parseInt(stringBuilderTemp.toString(), 16)
            println(temp)
            super.onCharacteristicRead(gatt, characteristic, status)
        }
    }


}