package com.example.app.ui.dialogs

import android.os.Bundle

/**
 * Created by Mariusz
 */
interface DialogFragmentCallback {
    fun onDialogResult(requestCode: Int, resultCode: Int, data: Bundle?)

    companion object {
        val RESULT_CANCEL = 0
        val RESULT_OK = -1
    }
}
