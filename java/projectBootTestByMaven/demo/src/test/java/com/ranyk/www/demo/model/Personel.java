package com.ranyk.www.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Personel {

    private String name;
    private Integer age;
    private Integer sex;

    public Personel(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.sex = 1;
    }

}