package com.cykei.roomtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity : 해당되는 클래스를 찾아 테이블로 변환한다.
// DB의 테이블명과 클래스명을 다르게하고 싶으면 @Entity(tableName = "테이블명") 으로 작성하자.

@Entity
class RoomMemo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var dateTime: Long = 0

    constructor(content: String, dateTime: Long){
        this.content = content
        this.dateTime = dateTime
    }
}