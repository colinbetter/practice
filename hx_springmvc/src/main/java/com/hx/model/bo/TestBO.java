package com.hx.model.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hx.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by xing on 2016/6/19.
 */
public class TestBO {
    private long id;
    private LocalDateTime dateTime;
    private List<ListTestBO> list;
    private Map<String,ListTestBO> map;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonSerialize(using =LocalDateTimeSerializer.class)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<ListTestBO> getList() {
        return list;
    }

    public void setList(List<ListTestBO> list) {
        this.list = list;
    }

    public Map<String, ListTestBO> getMap() {
        return map;
    }

    public void setMap(Map<String, ListTestBO> map) {
        this.map = map;
    }
}
