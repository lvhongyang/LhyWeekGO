package com.example.yuekao.JavaBean;

import java.util.List;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class Bean {

    /**
     * date : 20170922
     * stories : [{"images":["https://pic3.zhimg.com/v2-fbcc987cdb97f8e1859768945dbc4032.jpg"],"type":0,"id":9625444,"ga_prefix":"092209","title":"现实中的猩猩，是不是真的跟人一样存在着「文化」？"},{"images":["https://pic2.zhimg.com/v2-0c5475d56e9cca912f1a75056ee6e2f1.jpg"],"type":0,"id":3947050,"ga_prefix":"092208","title":"- 我们升级了全新硬件，虽然外表看不出来\r\n- 好的，不买"},{"images":["https://pic1.zhimg.com/v2-9fc6920af0087ab3db94ab47c3f7f260.jpg"],"type":0,"id":9621569,"ga_prefix":"092207","title":"首次办比赛就吸引到两大天王并肩出战，这个周末就看它了"},{"images":["https://pic1.zhimg.com/v2-3ec07817ea4cc7b1f57066d8426e05d0.jpg"],"type":0,"id":9626463,"ga_prefix":"092207","title":"我们在全国做了精神卫生调查，发现真实状况令人担忧"},{"images":["https://pic2.zhimg.com/v2-f3b974264dcb13bf56099126785afcf1.jpg"],"type":0,"id":9625570,"ga_prefix":"092207","title":"2017 年，观察到了一个有意思的消费升级现象"},{"images":["https://pic1.zhimg.com/v2-3e5331c27e27f31824bc9c239d1f1988.jpg"],"type":0,"id":9624986,"ga_prefix":"092206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-9c1568aa03ca151eea4a587ee51802ea.jpg","type":0,"id":9626463,"ga_prefix":"092207","title":"我们在全国做了精神卫生调查，发现真实状况令人担忧"},{"image":"https://pic1.zhimg.com/v2-05dc0ad139f217f283875815bc5538a0.jpg","type":0,"id":9626091,"ga_prefix":"092119","title":"薛之谦和李雨桐谁的截图是真的？专业图片后期鉴定师是这么说的"},{"image":"https://pic2.zhimg.com/v2-a4ebecc5f3ac7845805b2d17688db35d.jpg","type":0,"id":9622748,"ga_prefix":"092116","title":"什么是「消费升级」？为什么近两年人人都在说消费升级？"},{"image":"https://pic1.zhimg.com/v2-31c7577a439db633b92b2be42caf1e64.jpg","type":0,"id":9624305,"ga_prefix":"092109","title":"为什么你很少听说阅读障碍？因为在中国，他们统一被打成了「笨」"},{"image":"https://pic1.zhimg.com/v2-ccd5abcab2fe67c945245e1e8781d550.jpg","type":0,"id":9483544,"ga_prefix":"092107","title":"希望你和家人，不是只在 9 月 21 号这天才关心这个病"}]
     */

    public String date;
    public List<StoriesBean> stories;
    public List<TopStoriesBean> top_stories;

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-fbcc987cdb97f8e1859768945dbc4032.jpg"]
         * type : 0
         * id : 9625444
         * ga_prefix : 092209
         * title : 现实中的猩猩，是不是真的跟人一样存在着「文化」？
         */

        public int type;
        public int id;
        public String ga_prefix;
        public String title;
        public List<String> images;
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-9c1568aa03ca151eea4a587ee51802ea.jpg
         * type : 0
         * id : 9626463
         * ga_prefix : 092207
         * title : 我们在全国做了精神卫生调查，发现真实状况令人担忧
         */

        public String image;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }
}
