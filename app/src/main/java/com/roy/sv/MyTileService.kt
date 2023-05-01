package com.roy.sv

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import com.darkempire78.opencalculator.MainActivity

@RequiresApi(Build.VERSION_CODES.N)
class MyTileService : TileService() {

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(FLAG_ACTIVITY_NEW_TASK)

        startActivityAndCollapse(intent)
    }
}
