package com.don.onews_kotlin.bean

/**
 * Created by drcom on 2017/3/17.
 */

class HomeData {

    class ResultBean {

        var stat: String? = null
        var data: List<DataBean>? = null

        class DataBean {
            /**
             * uniquekey : b4b38b2f87bbc42c48cba1a95ff6c90f
             * title : 为这事，北京市委召开全会闭门学习 郭金龙严令：不容七嘴八舌
             * date : 2017-03-17 14:16
             * category : 头条
             * author_name : 北京日报
             * url : http://mini.eastday.com/mobile/170317141606697.html
             * thumbnail_pic_s : http://00.imgmini.eastday.com/mobile/20170317/20170317141606_1a72e6f0b7a84dd863430f1b8e9b5906_1_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://00.imgmini.eastday.com/mobile/20170317/20170317141606_1a72e6f0b7a84dd863430f1b8e9b5906_2_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://00.imgmini.eastday.com/mobile/20170317/20170317141606_876811bd6f1912e692615058e8f46f80_3_mwpm_03200403.jpeg
             */

            var uniquekey: String? = null
            var title: String? = null
            var date: String? = null
            var category: String? = null
            var author_name: String? = null
            var url: String? = null
            var thumbnail_pic_s: String? = null
            var thumbnail_pic_s02: String? = null
            var thumbnail_pic_s03: String? = null
        }
    }
}
