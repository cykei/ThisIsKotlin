package com.cykei.threadandcoroutine

import android.util.Log

class WorkerThread: Thread() {
    override fun run() {
        var i = 0
        while( i<10 ){
            i++
            Log.i("WorkerThread", "$i")
        }
    }
}