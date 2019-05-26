package pers.zhzh.logspringboot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.zhzh.logspringboot.constant.ResultType;

public class ParseUtil {
    public static Object toJSONByType(Object o, int resultType){
        switch (resultType){
            case ResultType.JSONOBJECT : return JSONObject.parseObject(String.valueOf(o));
            case ResultType.JSONARRAY : return JSONArray.parseArray(String.valueOf(o));
            default : return o;
        }
    }
}
