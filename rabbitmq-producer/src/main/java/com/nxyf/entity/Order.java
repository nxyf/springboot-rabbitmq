package com.nxyf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Order
 * @Description TODO
 * @Author nxyf
 * @Date 2021/3/13 8:38
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -2287447859715515611L;

    private String id;

    private String name;

    private String messageId;//存储消息发送的唯一标识
}
