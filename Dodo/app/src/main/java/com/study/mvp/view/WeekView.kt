package com.study.mvp.view

import com.study.bean.HotData

/**
 *  1. 周排行view层
 * 2. @author admin
 * 3. @date 2017/11/23 14:15
 */
interface WeekView {
    fun setHotData(data : HotData.HotData);
}