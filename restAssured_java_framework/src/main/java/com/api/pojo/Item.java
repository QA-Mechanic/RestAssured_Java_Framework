package com.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private String id;
    private String name;
    private Map<String, Object> dataMap;

    public Item() {
        
        this.dataMap = new HashMap<>();
    }

    public Item(String name) {
        this();
        this.name = name;
    }

    // Getters and setters
    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getName() {
        
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }

    public Map<String, Object> getDataMap() {
        
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        
        this.dataMap = dataMap;
    }

    public void addData(String key, Object value) {
        
        this.dataMap.put(key, value);
    }
}

