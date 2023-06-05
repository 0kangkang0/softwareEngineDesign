package com.kangkang.pojo;

import lombok.Data;

@Data
public class Orders {
    private String id;
    private String userId;
    private String ticketId;
    private String name;
    private String idNumber;
    private String tel;
    private String mail;
//    1:已订票；2:已取消；3:已检票；4:已值机  5.已改签
    private Integer status;
}
