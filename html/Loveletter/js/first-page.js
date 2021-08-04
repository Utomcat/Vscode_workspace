<!-- 自定义第一页的 js 样式 -->
function checkStatus(dicStatusCode, isReg, isHandled, isConfirmed, isArchived, deleted) {

}

/**
 * 转换对应的值为boolean值,根据传入的参数值,按照 1: true;其他值均为: false;
 *
 * @param value 传入的需要转换的值
 * @returns {boolean|*} 返回转换后的boolean类型值
 */
function conversionBoolean(value) {
    let type = typeof value;
    if (type === "boolean") {
        return value;
    } else if (type === "number") {
        return value === 1;
    } else if (type === "string") {
        return value === "1";
    } else {
        return false;
    }
}

/**
 * 初始化文本内容
 *
 * @param id 需要显示内容的组件ID
 */
function initContent(id) {
    $(id).html(getText());
    self.setInterval("calculationAcquaintanceTime()", 1000);
}

/**
 * 获取内容文本
 */
function getText() {
    return "<p>YJL: </P><br/>" +
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;嗯,当你看到这个网页(或者说是信)时,我也不知道是什么时候了,可能是21年5月份,也可能是更晚,我不知道此时的你会是在一个什么的情况下,也许是已经收到上岸的通知书,准备9月份的入学;亦或者是在开始进行忙碌的工作中." +
        "当然我更希望是第一种情况,这样你离你的理想就会更近一步了.如果是这样,那我也在此真心祝贺你了,(偷笑),这样我的饭就又有着落了(手动滑稽)</p><br/>" +
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;如果我没有算错的话,我们相识应该有<a id='acquaintanceTime' class='acquaintanceTime'></a>,不过时间过的真的很快,眨眼之间我们都高中毕业快七年了,感觉我现在都还没体验完学生呢就需要开始自己的生活了," +
        "而学生时代离我好像也是有点距离了,我在不是一个悲观的人,所以在这里我也只是感慨一下,尽管时间这么快,但是我们能在多年之后仍能取的联系,就我个人觉得我的运气还是挺不错的,先说说我自己吧.嗯从哪里说起呢,可能在你的记忆力可能就只停留在高中时期的的我了," +
        "每天定时定点的叫人打扫清洁、搬水,每月定时的收取水费.其他的话也基本上和班上的人没有太多的交集.所以那时候的我的性格是一个内向,而有不太愿意给自己找麻烦,老师和父母眼中的好孩子,当然学习成绩也就那样,不好不坏(至少那时候我自己是这样看的)." +
        "虽然小学初中的时候我也是一个淘气包,但是高中以后就没有之前的那种无忧无虑的想法和做法了,感觉不知不觉中会有很多的限制,而这些限制我又打不破,所以只能自己去适应它.大学以后呢,比之前开朗了些,但也没改变太多.进入工作以后,除了专业和从事的工作影响以外," +
        "更多的是我不太喜欢给自己太多的压力让自己变得很累,所以我为了让自己看起来能不那么成熟(进入工作以后称之为的现实),我就保持着那种幼稚而且感觉没有长大的感觉,虽然这样看着很轻松,但是生活是不会让我活的那么理想的.它会时不时给我的生活加点调料,让我过的更加" +
        "\"有滋有味\".所以你当时问我的一些人生的问题一下子把我问的有点懵,虽然自己思考过,但是对于自己没有想好的问题,我不知该怎么回答.就像当你问我的我对我自己以后的理想是什么时,我还真不知道怎么回你.所以就有了后面的我想做条\"咸鱼\"的回答.虽然当时也是有" +
        "想让你放松的想法,但是我觉得你当时肯定是吓了一跳吧.毕竟在那段时间中我个人(只是个人认为)觉得让你感觉愉快和轻松要比谈论这些严肃的问题要重要(因为那段时间对你来说很重要,要等待成绩、备考复试、填写志愿、收集资料、还有来自各个方面的压力等等)." +
        "现在我思考了一段时间后,思考的几个人生问题(可能有些不成熟,请勿见笑):<br/><ol><li>我有什么理想?</li><li>我想要的事业是什么?</li><li>我现在拥有的资产是什么?</li><li></li></ol></p><br/>" +
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;下一段</p>";
}

/**
 * 计算当前时间到指定时间的时间差
 */
function calculationAcquaintanceTime() {
    let nowDate = new Date();
    let oldDate = new Date("2011-08-20 00:00:00");
    let timeDifference = (nowDate - oldDate) / 1000;
    let days = parseInt("" + (timeDifference / 86400)); // 天  24*60*60*1000
    let hours = parseInt("" + (timeDifference / 3600)) - 24 * days;    // 小时 60*60 总小时数-过去的小时数=现在的小时数
    let minutes = parseInt("" + (timeDifference % 3600 / 60)); // 分钟 -(day*24) 以60秒为一整份 取余 剩下秒数 秒数/60 就是分钟数
    let seconds = parseInt("" + (timeDifference % 60));  // 以60秒为一整份 取余 剩下秒数
    let text = document.createTextNode(" " + days + " 天 " + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒 (距 2011-08-20,如果没有记错我们高一入学的时间的话)");
    document.getElementById("acquaintanceTime").innerText = text.data;
}