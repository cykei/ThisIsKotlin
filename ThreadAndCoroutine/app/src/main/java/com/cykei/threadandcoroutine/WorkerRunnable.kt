package com.cykei.threadandcoroutine

import android.util.Log

class WorkerRunnable: Runnable {
    override fun run() {
        var i = 0
        while( i< 10){
            i++
            Log.i("WorkerRunnable", "$i")
        }
    }
}