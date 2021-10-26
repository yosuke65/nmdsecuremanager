package com.nmd.common

import android.content.Context
import android.content.Intent
import com.nmd.core.base.EnvironmentManager
import com.nmd.core.network.NetworkConnector
import com.nmd.core.network.api.NetworkManager
import com.nmd.core.parser.model.AppEnvironment

private const val IS_MOCK_CALL = "isMockCall"
private const val APP_ENVIRONMENT = "appEnvironment"

fun setEnvironment(context: Context, intent: Intent? = null) {
    intent?.let {
        if(intent.getBooleanExtra(IS_MOCK_CALL, false)) {
            //Mock setup later
        } else {
            intent.extras?.get(APP_ENVIRONMENT)?.let { appEnvironment ->
                EnvironmentManager.appEnvironment = appEnvironment as AppEnvironment
                NetworkManager.setNetworkConnector(object : NetworkConnector{})
            }
        }
    }
}
