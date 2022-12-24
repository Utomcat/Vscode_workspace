package com.ranyk.www.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 账户Key
 * 
 * @author ranyk
 * @version V1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccTypeKeyBean {
    private String netId;
    private String curId;
    private String accType;
}
