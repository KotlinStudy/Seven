package com.study.bean

import java.io.Serializable

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  15:46
 */
class Bean :Serializable{


data class Bean(
		val issueList: List<Issue>,
		val nextPageUrl: String, //http://baobab.kaiyanapp.com/api/v2/feed?date=1511226000000&num=2
		val nextPublishTime: Long, //1511485200000
		val newestIssueType: String, //morning
		val dialog: Any //null
)

data class Issue(
		val releaseTime: Long, //1511398800000
		val type: String, //morning
		val date: Long, //1511398800000
		val publishTime: Long, //1511398800000
		val itemList: List<Item>,
		val count: Int //5
)

data class Item(
		val type: String, //video
		val data: Data,
		val tag: Any, //null
		val id: Int //0
)

data class Data(
		val dataType: String, //VideoBeanForClient
		val id: Int, //26630
		val title: String, //做一名闪电侠是什么体验？
		val slogan: String, //「二线」超级英雄的辛酸泪
		val description: String, //虽然坐拥 DC 的「第一外挂神力」，闪电侠始终不如超人、钢铁侠人气高。除此之外，超高速神力在生活中一定也有很多不便……From Anwar Jibawi
		val provider: Provider,
		val category: String, //搞笑
		val author: Any, //null
		val cover: Cover,
		val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26630&editionType=default&source=qcloud
		val thumbPlayUrl: String,
		val duration: Int, //198
		val webUrl: WebUrl,
		val releaseTime: Long, //1511398801000
		val library: String, //DAILY
		val playInfo: List<PlayInfo>,
		val consumption: Consumption,
		val campaign: Any, //null
		val waterMarks: Any, //null
		val adTrack: Any, //null
		val tags: List<Tag>,
		val type: String, //NORMAL
		val titlePgc: String,
		val descriptionPgc: String,
		val remark: String,
		val idx: Int, //0
		val shareAdTrack: Any, //null
		val favoriteAdTrack: Any, //null
		val webAdTrack: Any, //null
		val date: Long, //1511398800000
		val promotion: Any, //null
		val label: Any, //null
		val labelList: List<Any>,
		val descriptionEditor: String, //虽然坐拥 DC 的「第一外挂神力」，闪电侠始终不如超人、钢铁侠人气高。除此之外，超高速神力在生活中一定也有很多不便……From Anwar Jibawi
		val collected: Boolean, //false
		val played: Boolean, //false
		val subtitles: List<Any>,
		val lastViewTime: Any, //null
		val playlists: Any //null
)

data class Provider(
		val name: String, //YouTube
		val alias: String, //youtube
		val icon: String //http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
)

data class Consumption(
		val collectionCount: Int, //82
		val shareCount: Int, //59
		val replyCount: Int //4
)

data class PlayInfo(
		val height: Int, //480
		val width: Int, //854
		val urlList: List<Url>,
		val name: String, //标清
		val type: String, //normal
		val url: String //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26630&editionType=normal&source=qcloud
)

data class Url(
		val name: String, //qcloud
		val url: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26630&editionType=normal&source=qcloud
		val size: Int //14702061
)

data class Tag(
		val id: Int, //516
		val name: String, //恶搞
		val actionUrl: String, //eyepetizer://tag/516/?title=%E6%81%B6%E6%90%9E
		val adTrack: Any, //null
		val desc: Any, //null
		val bgPicture: String, //http://img.kaiyanapp.com/f884134104430c3fe511147e93ea914f.jpeg?imageMogr2/quality/100
		val headerImage: String, //http://img.kaiyanapp.com/f884134104430c3fe511147e93ea914f.jpeg?imageMogr2/quality/100
		val tagRecType: String //NORMAL
)

data class Cover(
		val feed: String, //http://img.kaiyanapp.com/360abfc22144a8d5e525616ca095122c.jpeg?imageMogr2/quality/60/format/jpg
		val detail: String, //http://img.kaiyanapp.com/360abfc22144a8d5e525616ca095122c.jpeg?imageMogr2/quality/60/format/jpg
		val blurred: String, //http://img.kaiyanapp.com/47df81dc171c30c20b0b5e31ceb226d1.jpeg?imageMogr2/quality/60/format/jpg
		val sharing: Any, //null
		val homepage: String //http://img.kaiyanapp.com/360abfc22144a8d5e525616ca095122c.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
)

data class WebUrl(
		val raw: String, //http://www.eyepetizer.net/detail.html?vid=26630
		val forWeibo: String //http://wandou.im/3np7q0
)
}