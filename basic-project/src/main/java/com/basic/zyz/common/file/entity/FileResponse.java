package com.basic.zyz.common.file.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 上传文件的自定义返回格式
 * @author zyz
 * @create: 2019-01-17
 */
public class FileResponse extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	static Map<String,Object> msgMap = new HashMap<>();

    public static String error(int code, String msg) throws JsonProcessingException {
        FileResponse result = new FileResponse();
        msgMap.put("msg",msg);
        result.put("code",code);
        result.put("error",msgMap);
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(result);
        return string;
    }

    public static String success(int code, String url) throws JsonProcessingException {
        FileResponse result = new FileResponse();
        result.put("msg","success");
        result.put("code",code);
        msgMap.put("src",url);
        result.put("data",msgMap);
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(result);
        return string;
    }
}
