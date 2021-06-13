package com.cykei.fileio

import android.content.Context
import java.io.*

class FileUtil {
    // 외, 내부 저장소 구별없이 사용가능. 단, 외부 저장소 사용하고 싶으면 권한 필요
    fun readTextFile(fullPath: String): String {
        val file = File(fullPath)
        if(!file.exists()) return ""

        val reader = FileReader(file)
        val buffer = BufferedReader(reader)
        var temp = ""
        val result = StringBuffer()
        while(true){
            temp = buffer.readLine()
            if(temp == null) break;
            else result.append(buffer)
        }
        buffer.close()
        return result.toString()
    }
    fun readTextFileSimple(fullPath: String, context:Context): String{
        var contents=""
        context.openFileInput(fullPath).bufferedReader().useLines {
            lines -> contents = lines.joinToString("\n")
        }
        return contents
    }

    fun writeTextFile(directory: String, filename:String, content: String) {
        val dir = File(directory)
        if(!dir.exists()) {
            dir.mkdirs()
        }
        val writer = FileWriter(directory + "/" + filename)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
    }

    fun writeTextFileSimple(directory: String, filename:String, content: String, context: Context) {
        val contents = "Hello\nWorld!"
        context.openFileOutput(filename, Context.MODE_PRIVATE).use { stream ->
            // Context.MODE_APPEND : 기존파일에 덧붙인다.
            stream.write(contents.toByteArray())
        }
    }
}