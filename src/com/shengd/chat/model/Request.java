package com.shengd.chat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by da on 8/18/17.
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private RequestType type;
    private Map<String, Object> contentsMap;

    public Request() {
        contentsMap = new HashMap<String, Object>();
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public Map<String, Object> getContents() {
        return contentsMap;
    }

    public Object getContent(String name) {
        return contentsMap.get(name);
    }

    public void addContent(String name, Object content) {
        contentsMap.put(name, content);
    }
}
