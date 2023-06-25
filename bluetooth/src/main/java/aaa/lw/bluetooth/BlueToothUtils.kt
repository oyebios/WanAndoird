package aaa.lw.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context


class BlueToothUtils {

    var bluetoothAdapter: BluetoothAdapter? = null
    fun isDeviceSupportBlueTooth(context: Context): Boolean {
        val bluetoothManager: BluetoothManager =
            context.getSystemService(BluetoothManager::class.java) ?: return false
        if (bluetoothManager.adapter == null)
            return false
        bluetoothAdapter = bluetoothManager.adapter
        return true
    }


    @SuppressLint("MissingPermission")
    fun isBlueToothEnable(): Boolean {
        return bluetoothAdapter?.run {
            enable()
        } ?: false
    }
}