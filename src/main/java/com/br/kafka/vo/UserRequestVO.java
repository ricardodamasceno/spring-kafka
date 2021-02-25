package com.br.kafka.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestVO {

    private String name;
    private String email;
    private String phone;
    private Integer age;

}
