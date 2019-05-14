package com.example.weather.models.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.PermissionChecker.checkCallingOrSelfPermission

object PermissionManager {
    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(context, permissionArray[i]) == PackageManager.PERMISSION_DENIED) {
                allSuccess = false
            }
        }
        return allSuccess
    }
}