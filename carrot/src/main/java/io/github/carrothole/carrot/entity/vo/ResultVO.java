package io.github.carrothole.carrot.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 * date: 2024-9-14 20:58
 *
 * @author moon
 * @since 0.0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResultVO<T> implements Serializable {

    private String msg;

    private String message;

    private T data;

    private Integer code;
}
