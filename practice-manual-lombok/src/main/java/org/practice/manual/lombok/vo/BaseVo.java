package org.practice.manual.lombok.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BaseVo {

    private Long id;

    private String createUser;

    private Date createTime;
}
