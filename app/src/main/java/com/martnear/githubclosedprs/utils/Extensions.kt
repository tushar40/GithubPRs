package com.martnear.githubclosedprs.utils

import androidx.annotation.StringRes
import com.martnear.githubclosedprs.MainApplication

fun getStringResource(@StringRes resId: Int, vararg arg: Any?): String = MainApplication.INSTANCE.resources.getString(resId, *arg)
