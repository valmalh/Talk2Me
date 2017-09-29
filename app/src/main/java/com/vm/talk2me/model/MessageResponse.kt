package com.vm.talk2me.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageResponse {

    @SerializedName("result")
    @Expose
    var result: Int? = null
    @SerializedName("response")
    @Expose
    var response: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null

}