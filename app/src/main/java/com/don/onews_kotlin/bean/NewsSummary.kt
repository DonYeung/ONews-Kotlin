package com.don.onews_kotlin.bean

import android.os.Parcel
import android.os.Parcelable

import java.util.ArrayList

/**
 * Created by drcom on 2017/3/27.
 */

/**
 * des:新闻消息实体类
 * Created by xsf
 * on 2016.06.13:05
 */
class NewsSummary : Parcelable {
    var postid: String? = null
    var isHasCover: Boolean = false
    var hasHead: Int = 0
    var replyCount: Int = 0
    var hasImg: Int = 0
    var digest: String? = null
    var isHasIcon: Boolean = false
    var docid: String? = null
    var title: String? = null
    var ltitle: String? = null
    var order: Int = 0
    var priority: Int = 0
    var lmodify: String? = null
    var boardid: String? = null
    var photosetID: String? = null
    var template: String? = null
    var votecount: Int = 0
    var skipID: String? = null
    var alias: String? = null
    var skipType: String? = null
    var cid: String? = null
    var hasAD: Int = 0
    var source: String? = null
    var ename: String? = null
    var imgsrc: String? = null
    var tname: String? = null
    var ptime: String? = null
    /**
     * title : "悬崖村" 孩子上学需爬800米悬崖
     * tag : photoset
     * imgsrc : http://img1.cache.netease.com/3g/2016/5/24/2016052421435478ea5.jpg
     * subtitle :
     * url : 00AP0001|119327
     */

    var ads: List<AdsBean>? = null
    /**
     * imgsrc : http://img3.cache.netease.com/3g/2016/5/24/2016052416484243560.jpg
     */

    var imgextra: List<ImgextraBean>? = null

    class AdsBean {
        var title: String? = null
        var tag: String? = null
        var imgsrc: String? = null
        var subtitle: String? = null
        var url: String? = null
    }

    class ImgextraBean {
        var imgsrc: String? = null
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.postid)
        dest.writeByte(if (isHasCover) 1.toByte() else 0.toByte())
        dest.writeInt(this.hasHead)
        dest.writeInt(this.replyCount)
        dest.writeInt(this.hasImg)
        dest.writeString(this.digest)
        dest.writeByte(if (isHasIcon) 1.toByte() else 0.toByte())
        dest.writeString(this.docid)
        dest.writeString(this.title)
        dest.writeString(this.ltitle)
        dest.writeInt(this.order)
        dest.writeInt(this.priority)
        dest.writeString(this.lmodify)
        dest.writeString(this.boardid)
        dest.writeString(this.photosetID)
        dest.writeString(this.template)
        dest.writeInt(this.votecount)
        dest.writeString(this.skipID)
        dest.writeString(this.alias)
        dest.writeString(this.skipType)
        dest.writeString(this.cid)
        dest.writeInt(this.hasAD)
        dest.writeString(this.source)
        dest.writeString(this.ename)
        dest.writeString(this.imgsrc)
        dest.writeString(this.tname)
        dest.writeString(this.ptime)
        dest.writeList(this.ads)
        dest.writeList(this.imgextra)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.postid = `in`.readString()
        this.isHasCover = `in`.readByte().toInt() != 0
        this.hasHead = `in`.readInt()
        this.replyCount = `in`.readInt()
        this.hasImg = `in`.readInt()
        this.digest = `in`.readString()
        this.isHasIcon = `in`.readByte().toInt() != 0
        this.docid = `in`.readString()
        this.title = `in`.readString()
        this.ltitle = `in`.readString()
        this.order = `in`.readInt()
        this.priority = `in`.readInt()
        this.lmodify = `in`.readString()
        this.boardid = `in`.readString()
        this.photosetID = `in`.readString()
        this.template = `in`.readString()
        this.votecount = `in`.readInt()
        this.skipID = `in`.readString()
        this.alias = `in`.readString()
        this.skipType = `in`.readString()
        this.cid = `in`.readString()
        this.hasAD = `in`.readInt()
        this.source = `in`.readString()
        this.ename = `in`.readString()
        this.imgsrc = `in`.readString()
        this.tname = `in`.readString()
        this.ptime = `in`.readString()
        this.ads = ArrayList<AdsBean>()
        `in`.readList(this.ads, AdsBean::class.java.classLoader)
        this.imgextra = ArrayList<ImgextraBean>()
        `in`.readList(this.imgextra, ImgextraBean::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<NewsSummary> = object : Parcelable.Creator<NewsSummary> {
            override fun createFromParcel(source: Parcel): NewsSummary {
                return NewsSummary(source)
            }

            override fun newArray(size: Int): Array<NewsSummary?> {
                return arrayOfNulls(size)
            }
        }
    }
}