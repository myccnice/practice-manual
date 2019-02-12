package org.practice.manual.lombok.features;

import org.practice.manual.lombok.vo.UserBaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class DataExample extends UserBaseVo {

    @NonNull
    private Integer age;
    private double score;
    private String[] tags;
}
