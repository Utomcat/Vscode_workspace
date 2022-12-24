package com.ranyk.www.demo.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 账户信息类
 * 
 * @author ranyk
 * @version V1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccInterestInfo {
    private  AccTypeKeyBean accTypeKeyBean;
    private Map<String,Object> attributeMap;
    private List<String> attributeList;

}
