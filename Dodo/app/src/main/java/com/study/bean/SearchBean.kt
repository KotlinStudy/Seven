package com.study.bean

/**
 * Created by XIn💕 on 2017/12/1.
 */
class SearchBean {

data class SearchBean(
		val itemList: List<Item>,
		val count: Int, //10
		val total: Int, //10325
		val nextPageUrl: String //http://baobab.kaiyanapp.com/api/v1/search?start=20&num=10&query=%E4%BD%A0
)

data class Item(
		val type: String, //video
		val data: Data,
		val tag: Any //null
)

data class Data(
		val dataType: String, //VideoBeanForClient
		val id: Int, //6792
		val title: String, //驾照知识 get：为远行做准备
		val slogan: Any, //null
		val description: String, //「你会开车吗」这句话如今等同于「你吃了吗」。拥有中国驾照，你知道如何玩转地球吗？短片用创意动画的形式，教你用一本驾照游世界。假期模式即将开启，带上你的驾照，撒欢儿开车去吧~From @趣你的车
		val provider: Provider,
		val category: String, //科普
		val author: Author,
		val cover: Cover,
		val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=6792&editionType=default&source=qcloud
		val thumbPlayUrl: Any, //null
		val duration: Int, //117
		val webUrl: WebUrl,
		val releaseTime: Long, //1463805240000
		val library: String, //DAILY
		val playInfo: List<PlayInfo>,
		val consumption: Consumption,
		val campaign: Any, //null
		val waterMarks: Any, //null
		val adTrack: Any, //null
		val tags: List<Tag>,
		val type: String, //NORMAL
		val titlePgc: Any, //null
		val descriptionPgc: Any, //null
		val remark: Any, //null
		val idx: Int, //0
		val shareAdTrack: Any, //null
		val favoriteAdTrack: Any, //null
		val webAdTrack: Any, //null
		val date: Long, //1463805240000
		val promotion: Any, //null
		val label: Any, //null
		val labelList: List<Any>,
		val descriptionEditor: String, //「你会开车吗」这句话如今等同于「你吃了吗」。拥有中国驾照，你知道如何玩转地球吗？短片用创意动画的形式，教你用一本驾照游世界。假期模式即将开启，带上你的驾照，撒欢儿开车去吧~From @趣你的车
		val collected: Boolean, //false
		val played: Boolean, //false
		val subtitles: List<Any>,
		val lastViewTime: Any, //null
		val playlists: Any //null
)

data class Consumption(
		val collectionCount: Int, //3545
		val shareCount: Int, //1914
		val replyCount: Int //39
)

data class WebUrl(
		val raw: String, //http://www.eyepetizer.net/detail.html?vid=6792
		val forWeibo: String //http://wandou.im/1z9toe
)

data class PlayInfo(
		val height: Int, //480
		val width: Int, //854
		val urlList: List<Url>,
		val name: String, //标清
		val type: String, //normal
		val url: String //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=6792&editionType=normal&source=qcloud
)

data class Url(
		val name: String, //qcloud
		val url: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=6792&editionType=normal&source=qcloud
		val size: Int //10501712
)

data class Cover(
		val feed: String, //http://img.kaiyanapp.com/7dde9cdd39c07a175e3fdd5800bedfdd.jpeg?imageMogr2/quality/100
		val detail: String, //http://img.kaiyanapp.com/7dde9cdd39c07a175e3fdd5800bedfdd.jpeg?imageMogr2/quality/100
		val blurred: String, //http://img.kaiyanapp.com/a69029f975b1122ff798b7897225cab0.jpeg?imageMogr2/quality/100
		val sharing: Any, //null
		val homepage: Any //null
)

data class Tag(
		val id: Int, //44
		val name: String, //科普
		val actionUrl: String, //eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE
		val adTrack: Any, //null
		val desc: Any, //null
		val bgPicture: String, //http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
		val headerImage: String, //http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
		val tagRecType: String //NORMAL
)

data class Provider(
		val name: String, //乐视
		val alias: String, //letv
		val icon: String //http://img.kaiyanapp.com/784da0db524cf8e1448574a764dcdca6.png
)

data class Author(
		val id: Int, //2171
		val icon: String, //http://img.kaiyanapp.com/0117b9108c7cff43700db8af5e24f2bf.jpeg
		val name: String, //开眼科普精选
		val description: String, //每天获得新知识
		val link: String,
		val latestReleaseTime: Long, //1511847302000
		val videoNum: Int, //193
		val adTrack: Any, //null
		val follow: Follow,
		val shield: Shield,
		val approvedNotReadyVideoCount: Int, //0
		val ifPgc: Boolean //true
)

data class Shield(
		val itemType: String, //author
		val itemId: Int, //2171
		val shielded: Boolean //false
)

data class Follow(
		val itemType: String, //author
		val itemId: Int, //2171
		val followed: Boolean //false
)

}