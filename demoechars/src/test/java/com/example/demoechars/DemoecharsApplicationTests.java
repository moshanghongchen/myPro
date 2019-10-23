package com.example.demoechars;

import com.example.demoechars.model.entity.EcharsTable;
import com.example.demoechars.model.entity.EcharsTableExample;
import com.example.demoechars.model.mapper.EcharsTableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoecharsApplicationTests {
    @Autowired
    EcharsTableMapper mapper;

    @Test
    public void contextLoads() {
        final List<EcharsTable> list = mapper.selectByExample(new EcharsTableExample());
        Collections.sort(list);
        list.forEach(item->{
            System.out.println(item.getId());
        });

    }

}
