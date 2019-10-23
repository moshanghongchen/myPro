package com.example.demoechars.model.service;

import com.example.demoechars.model.entity.EcharsTable;
import com.example.demoechars.model.entity.EcharsTableExample;
import com.example.demoechars.model.mapper.EcharsTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EcharsTableService {
    @Autowired
    EcharsTableMapper echarsTableMapper;

    public ArrayList<EcharsTable> getAll(String type){
        //查询条件
        String qs=null;
        if("1".equals(type)){
            qs=EcharsTable.TYPE_TZ;
        }else {
            qs=EcharsTable.TYPE_YD;
        }

        //结果集
        ArrayList<EcharsTable> resList = new ArrayList<EcharsTable>();

        //获取所有的数据
        EcharsTableExample echarsTableExample = new EcharsTableExample();
        echarsTableExample.createCriteria().andTypeEqualTo(qs);
        final List<EcharsTable> echarsTables = echarsTableMapper.selectByExample(echarsTableExample);
        Collections.sort(echarsTables);
        //获取不同的name
        final Set<String> names = getNames(echarsTables);
        //获取不同的日期
        final Set<String> dates = getDates(echarsTables);

        //获取数据
        names.forEach(name->{
            final EcharsTable echarsTable = new EcharsTable(name);
            echarsTable.getDates().addAll(dates);
            dates.forEach(date->{

                final Date start = DateParse(date + " 00:00:00");
                final Date end = DateParse(date + " 23:59:59");
                echarsTableExample.clear();
                echarsTableExample.createCriteria().andTypeEqualTo(EcharsTable.TYPE_TZ)
                .andNameEqualTo(name).andDateBetween(start,end);
                final List<EcharsTable> elist = echarsTableMapper.selectByExample(echarsTableExample);
                if (elist==null||elist.size()==0){
                    echarsTable.getDatas().add("0");
                }else {
                    echarsTable.getDatas().add(elist.get(0).getData());
                }
            });
            resList.add(echarsTable);
        });

        return resList;
    }

    public int save(EcharsTable echarsTable) {

        echarsTable.setType("体重");
        return echarsTableMapper.insertSelective(echarsTable);
    }

    public Map<String, Object> queryAll() {
        final HashMap<String, Object> map = new HashMap<>();
        final EcharsTableExample example = new EcharsTableExample();
        example.createCriteria().andTypeEqualTo(EcharsTable.TYPE_TZ);
        final List<EcharsTable> echarsTables = echarsTableMapper.selectByExample(example);
        map.put("rows", echarsTables);
        map.put("total", echarsTables.size());
        return map;
    }

    public Set<String> getDates(List<EcharsTable> list) {
        final LinkedHashSet<String> set = new LinkedHashSet<>();
        list.forEach(item -> {
            final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            final String format1 = format.format(item.getDate());
            set.add(format1);
        });
        return set;
    }

    public Set<String> getNames(List<EcharsTable> list){
        final HashSet<String> set = new HashSet<>();
        list.forEach(item -> {
            set.add(item.getName());
        });
        return set;
    }

    public String DateFormat(Date date){
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }
    public Date DateParse(String date){
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date res=null;
        try{
            res=format.parse(date);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }

}