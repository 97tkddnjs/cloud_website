package com.marine.website.visualization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GeoTempRepository {

    private static final Map<Long, GeoItem> store = new HashMap<>(); // static 썼다는 것에 주의 and 원래 HashMap 쓰면 X

    private static long sequence = 0L; // static

    public GeoItem save(GeoItem item) {


        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public GeoItem findById(Long id) {
        return store.get(id);
    }

    public List<GeoItem> findAll() {
        return new ArrayList<>(store.values());// store의 값을 안전하게 하기 위해서 한번 감싼 것임
    }

}
