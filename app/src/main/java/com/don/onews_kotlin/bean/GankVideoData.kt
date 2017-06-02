package com.don.onews_kotlin.bean

/**
 * Created by drcom on 2017/3/23.
 */

class GankVideoData {

    /**
     * error : false
     * results : [{"_id":"58d0a5bf421aa90efc4fb700","createdAt":"2017-03-21T12:02:07.706Z","desc":"护送美国总统，简直是美国特勤人员每天重复的噩梦。","publishedAt":"2017-03-22T11:47:09.555Z","source":"chrome","type":"休息视频","url":"http://www.miaopai.com/show/I-DpJqf6~PbTKlSq04Hofw__.htm","used":true,"who":"lxxself"}]
     */

    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * _id : 58d0a5bf421aa90efc4fb700
         * createdAt : 2017-03-21T12:02:07.706Z
         * desc : 护送美国总统，简直是美国特勤人员每天重复的噩梦。
         * publishedAt : 2017-03-22T11:47:09.555Z
         * source : chrome
         * type : 休息视频
         * url : http://www.miaopai.com/show/I-DpJqf6~PbTKlSq04Hofw__.htm
         * used : true
         * who : lxxself
         */

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: String? = null
    }
}
