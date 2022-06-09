package com.hacker.entity;

import com.hacker.common.annotation.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zero
 * @Date: 2022/6/7 15:23
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Element(length = "10",order = "1")
    private String schoolId;

    @Element(length = "3",order = "2")
    private String name;

    @Element(length = "2",order = "3")
    private String age;

    @Element(length = "6",order = "4")
    private String address;

    @Element(length = "6",order = "5")
    private String school;


}
