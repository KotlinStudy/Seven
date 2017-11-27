package com.study.bean

import java.io.Serializable

/**
 *  1. 类的用途
 * 2. @author admin
 * 3. @date 2017/11/23 16:03
 */
class HotData {

data class HotData(
		val itemList: List<Item>,
		val count: Int, //35
		val total: Int, //0
		val nextPageUrl: Any //null
)

data class Item(
		val type: String, //video
		val data: Data,
		val tag: Any, //null
		val id: Int //0
)

data class Data (
		val dataType: String, //VideoBeanForClient
		val id: Int, //26150
		val title: String, //一瓶矿泉水如何毁掉一部大片
		val slogan: String, //结局神转折：创意矿泉水广告「渴」
		val description: String, //曾经有一瓶珍贵的水放在我面前，我没有珍惜。直到开拍之后，才追悔莫及……这条矿泉水广告是成立于 1985 年的伦敦广告公司 Blink 所做，导演 Los Pérez。 From Blink
		val provider: Provider,
		val category: String, //广告
		val author: Author,
		val cover: Cover,
		val playUrl: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26150&editionType=default&source=qcloud
		val thumbPlayUrl: String,
		val duration: Int, //97
		val webUrl: WebUrl,
		val releaseTime: Long, //1510880401000
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
		val remark: Any, //null
		val idx: Int, //0
		val shareAdTrack: Any, //null
		val favoriteAdTrack: Any, //null
		val webAdTrack: Any, //null
		val date: Long, //1510880401000
		val promotion: Any, //null
		val label: Any, //null
		val labelList: List<Any>,
		val descriptionEditor: String, //曾经有一瓶珍贵的水放在我面前，我没有珍惜。直到开拍之后，才追悔莫及……这条矿泉水广告是成立于 1985 年的伦敦广告公司 Blink 所做，导演 Los Pérez。 From Blink
		val collected: Boolean, //false
		val played: Boolean, //false
		val subtitles: List<Any>,
		val lastViewTime: Any, //null
		val playlists: Any //null
)

data class Provider(
		val name: String, //Vimeo
		val alias: String, //vimeo
		val icon: String //http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png
)

data class PlayInfo(
		val height: Int, //480
		val width: Int, //854
		val urlList: List<Url>,
		val name: String, //标清
		val type: String, //normal
		val url: String //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26150&editionType=normal&source=qcloud
)

data class Url(
		val name: String, //qcloud
		val url: String, //http://baobab.kaiyanapp.com/api/v1/playUrl?vid=26150&editionType=normal&source=qcloud
		val size: Int //11950979
)

data class Author(
		val id: Int, //2162
		val icon: String, //http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
		val name: String, //开眼广告精选
		val description: String, //为广告人的精彩创意点赞
		val link: String,
		val latestReleaseTime: Long, //1511312400000
		val videoNum: Int, //805
		val adTrack: Any, //null
		val follow: Follow,
		val shield: Shield,
		val approvedNotReadyVideoCount: Int, //0
		val ifPgc: Boolean //true
)

data class Shield(
		val itemType: String, //author
		val itemId: Int, //2162
		val shielded: Boolean //false
)

data class Follow(
		val itemType: String, //author
		val itemId: Int, //2162
		val followed: Boolean //false
)

data class Consumption(
		val collectionCount: Int, //852
		val shareCount: Int, //731
		val replyCount: Int //25
)

data class WebUrl(
		val raw: String, //http://www.eyepetizer.net/detail.html?vid=26150
		val forWeibo: String //http://wandou.im/3np6zw
)

data class Cover(
		val feed: String, //http://img.kaiyanapp.com/451103a6793e8879f3d6a74bb280f515.jpeg?imageMogr2/quality/60/format/jpg
		val detail: String, //http://img.kaiyanapp.com/451103a6793e8879f3d6a74bb280f515.jpeg?imageMogr2/quality/60/format/jpg
		val blurred: String, //http://img.kaiyanapp.com/341731cee59ff664e7014a2061fc0de4.jpeg?imageMogr2/quality/60/format/jpg
		val sharing: Any, //null
		val homepage: String //http://img.kaiyanapp.com/451103a6793e8879f3d6a74bb280f515.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
)

data class Tag(
		val id: Int, //2
		val name: String, //创意
		val actionUrl: String, //eyepetizer://tag/2/?title=%E5%88%9B%E6%84%8F
		val adTrack: Any, //null
		val desc: String, //创意十足的style
		val bgPicture: String, //http://img.kaiyanapp.com/e6eee049dd13fe8ce0712a6f2648d7e2.jpeg?imageMogr2/quality/100
		val headerImage: String, //http://img.kaiyanapp.com/fdefdb34cbe3d2ac9964d306febe9025.jpeg?imageMogr2/quality/100
		val tagRecType: String //NORMAL
)
}