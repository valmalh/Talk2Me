package com.vm.talk2me.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by VanManh on 01-Sep-17.
 */

class Message : Parcelable {
    var tag: Int = 0
    var mess: String? = null
    var timeFomated: String? = null

    constructor(tag: Int, mess: String, timeFomated: String) {
        this.tag = tag
        this.mess = mess
        this.timeFomated = timeFomated
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.tag)
        dest.writeString(this.mess)
        dest.writeString(this.timeFomated)
    }

    protected constructor(`in`: Parcel) {
        this.tag = `in`.readInt()
        this.mess = `in`.readString()
        this.timeFomated = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }

}
