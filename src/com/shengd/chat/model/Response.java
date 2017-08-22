package com.shengd.chat.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by da on 8/20/17.
 */
public class Response  implements Serializable {
    private static final long serialVersionUID = 1L;

    private ResponseType type;
    private ResponseStatus status;
    private Map<String, Object> contentsMap;

    public Response() {
        contentsMap = new HashMap<String, Object>();
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
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
