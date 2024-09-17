package io.github.carrothole.carrot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.carrothole.carrot.entity.vo.ResultVO;
import io.github.carrothole.carrot.exception.CarrotException;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
/**
 * Description:
 * date: 2024-9-14 21:13
 *
 * @author moon
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
@Priority(1)
public class GlobalResultHandler<T> implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 这里可以添加逻辑来判断是否支持该类型的返回值
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 如果返回值已经是ResultVO类型，则不需要进一步处理
        if (body instanceof ResultVO) {
            return body;
        }

        // 如果返回值不是ResultVO类型，则包装为ResultVO
        final ResultVO<Object> res = ResultVO.builder()
                .msg("成功")
                .data(body)
                .code(200)
                .build();
        if (body instanceof String){
            try {
                return objectMapper.writeValueAsString(res);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    @ExceptionHandler(CarrotException.class)
    @ResponseBody
    public ResultVO<Object> handleAllExceptions(CarrotException ex) {
        ex.printStackTrace();
        log.error(ex.getMsg());
        return ResultVO.builder().code(ex.getCode()).msg(ex.getMsg()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<Object> handleYourSpecificException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResultVO.builder().code(500).msg("服务器异常，请联系管理员").message(ex.getMessage()).build();
    }
}
