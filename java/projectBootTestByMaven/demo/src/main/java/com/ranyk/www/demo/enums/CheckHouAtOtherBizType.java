package com.ranyk.www.demo.enums;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * CLASS_NAME: CheckHouAtOtherBizType<br/>
 * Description: 其他系统检测房屋状态枚举对象<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public enum CheckHouAtOtherBizType {

    /**
     * 房屋修改时的检测配置
     */
    HOU_MODIFY_CHECK("修改",Boolean.TRUE,Boolean.FALSE,Boolean.TRUE,Boolean.TRUE),
    /**
     * 默认的检测配置
     */
    HOU_DEFAULT_CHECK("默认",Boolean.TRUE,Boolean.TRUE,Boolean.TRUE,Boolean.TRUE);

    /**
     * 需要检测的操类型
     */
    private final String name;
    /**
     * 商品房预售方案检测
     */
    private final Boolean checkChsBlue;
    /**
     * 商品房网签检测
     */
    private final Boolean checkChsCont;
    /**
     * 不动产办理检测
     */
    private final Boolean checkEstate;
    /**
     * 存量房网签检测
     */
    private final Boolean checkShsCont;

    /**
     * 全参构造函数
     *
     * @param name         需要检测其他系统的操作名
     * @param checkChsBlue 检测房屋是否在商品房预售方案办理中
     * @param checkChsCont 检测房屋是否在商品房网签办理中
     * @param checkEstate  检测房屋是否在不动产办理中
     * @param checkShsCont 检测房屋是否在存量房网签办理中
     */
    CheckHouAtOtherBizType(String name, Boolean checkChsBlue, Boolean checkChsCont, Boolean checkEstate, Boolean checkShsCont) {
        this.name = name;
        this.checkChsBlue = checkChsBlue;
        this.checkChsCont = checkChsCont;
        this.checkEstate = checkEstate;
        this.checkShsCont = checkShsCont;
    }

    public String getName() {
        return name;
    }

    public Boolean getCheckChsBlue() {
        return checkChsBlue;
    }

    public Boolean getCheckChsCont() {
        return checkChsCont;
    }

    public Boolean getCheckEstate() {
        return checkEstate;
    }

    public Boolean getCheckShsCont() {
        return checkShsCont;
    }


    /**
     * 通过指定的操作类型,获取对应操作的配置系统检测对象
     *
     * @param name 操作类型名称
     * @return 返回获取到的对应名称的配置检测对象,如果没有获取到指定的配置则返回默认的配置对象
     */
    public static CheckHouAtOtherBizType getTypeByName(String name) {
        if (!StringUtils.hasLength(name)){
            name = "默认";
        }
        for (CheckHouAtOtherBizType type : CheckHouAtOtherBizType.values()) {
            if (type.getName().equals(name)){
                return type;
            }
        }
        log.error("获取指定的检测其他系统中房屋状态配置时,未获取到指定的配置,将采用获取默认的值!");
        return getTypeByName("默认");
    }
}
