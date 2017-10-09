package com.hzgc.hbase.device;

import com.hzgc.dubbo.device.WarnRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PutData {
    public static List<WarnRule> setwarnRuleData(){
        List<WarnRule> rules = new ArrayList<WarnRule>();
        WarnRule warnRule1 = new WarnRule();
        warnRule1.setCode(0);
        warnRule1.setDayThreshold(3);
        warnRule1.setThreshold(70);
        warnRule1.setObjectType("police");
        rules.add(warnRule1);
        WarnRule warnRule2 = new WarnRule();
        warnRule2.setCode(0);
        warnRule2.setDayThreshold(10);
        warnRule2.setThreshold(80);
        warnRule2.setObjectType("wolf");
        rules.add(warnRule2);
        WarnRule warnRule3 = new WarnRule();
        warnRule3.setCode(0);
        warnRule3.setDayThreshold(4);
        warnRule3.setThreshold(78);
        warnRule3.setObjectType("student");
        rules.add(warnRule3);
        WarnRule warnRule4 = new WarnRule();
        warnRule4.setCode(0);
        warnRule4.setDayThreshold(12);
        warnRule4.setThreshold(68);
        warnRule4.setObjectType("teacher");
        rules.add(warnRule4);
        WarnRule warnRule5 = new WarnRule();
        warnRule5.setCode(0);
        warnRule5.setDayThreshold(2);
        warnRule5.setThreshold(56);
        warnRule5.setObjectType("thief");
        rules.add(warnRule5);
        WarnRule warnRule6 = new WarnRule();
        warnRule6.setCode(1);
        warnRule6.setDayThreshold(3);
        warnRule6.setThreshold(45);
        warnRule6.setObjectType("tiger");
        rules.add(warnRule6);
        WarnRule warnRule7 = new WarnRule();
        warnRule7.setCode(1);
        warnRule7.setDayThreshold(10);
        warnRule7.setThreshold(90);
        warnRule7.setObjectType("student");
        rules.add(warnRule7);
        WarnRule warnRule8 =new WarnRule();
        warnRule8.setCode(1);
        warnRule8.setDayThreshold(5);
        warnRule8.setThreshold(99);
        warnRule8.setObjectType("teacher");
        rules.add(warnRule8);
        WarnRule warnRule9 = new WarnRule();
        warnRule9.setCode(1);
        warnRule9.setDayThreshold(4);
        warnRule9.setThreshold(89);
        warnRule9.setObjectType("student");
        rules.add(warnRule9);
        WarnRule warnRule10 = new WarnRule();
        warnRule10.setCode(1);
        warnRule10.setDayThreshold(5);
        warnRule10.setThreshold(67);
        warnRule10.setObjectType("farmer");
        rules.add(warnRule10);
        WarnRule warnRule11 = new WarnRule();
        warnRule11.setCode(2);
        warnRule11.setDayThreshold(6);
        warnRule11.setThreshold(79);
        warnRule11.setObjectType("student");
        rules.add(warnRule11);
        WarnRule warnRule12 = new WarnRule();
        warnRule12.setCode(2);
        warnRule12.setDayThreshold(7);
        warnRule12.setThreshold(80);
        warnRule12.setObjectType("thief");
        rules.add(warnRule12);
        WarnRule warnRule13 = new WarnRule();
        warnRule13.setCode(2);
        warnRule13.setDayThreshold(2);
        warnRule13.setThreshold(77);
        warnRule13.setObjectType("teacher");
        rules.add(warnRule13);
        WarnRule warnRule14 = new WarnRule();
        warnRule14.setCode(2);
        warnRule14.setDayThreshold(9);
        warnRule14.setThreshold(100);
        warnRule14.setObjectType("businessman");
        rules.add(warnRule14);
        WarnRule warnRule15 = new WarnRule();
        warnRule15.setCode(2);
        warnRule15.setDayThreshold(1);
        warnRule15.setThreshold(99);
        warnRule15.setObjectType("farmer");
        rules.add(warnRule15);
        return rules;
    }
    public static List<String> setIpcId(){
        List<String> list = new ArrayList<>();
        list.add("hhh100");
        list.add("hhh200");
        list.add("hhh300");
        list.add("djgj100");
        list.add("djgj200");
        list.add("qyy111");
        list.add("qyy222");
        list.add("yyy111");
        list.add("yyy222");
        list.add("hhh100x");
        list.add("hhh200x");
        list.add("hhh300x");
        list.add("djgj100x");
        list.add("djgj200x");
        list.add("qyy111x");
        list.add("qyy222x");
        list.add("yyy111x");
        list.add("yyy222x");
        return list;
    }

    public static Map<Integer, Map<String, Integer>> commonRule(){
        Map<Integer, Map<String, Integer>> commonRule = new HashMap<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("student",70);
        map.put("teacher",99);
        map.put("tiger",88);
        map.put("thief",77);
        map.put("farmer",66);
        commonRule.put(0,map);
        Map<String,Integer> map1 = new HashMap<>();
        map1.put("father",67);
        map1.put("mother",87);
        map1.put("dady",56);
        map1.put("mom",55);
        map1.put("grands",85);
        commonRule.put(1,map1);
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("brother",98);
        map2.put("sister",76);
        map2.put("wolf",64);
        map2.put("cat",83);
        map2.put("dog",93);
        commonRule.put(2,map2);
        return commonRule;
    }
    public static Map<String, Map<String, Integer>> offlineMap(){
        Map<String, Map<String, Integer>> offlineMap = new HashMap<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("student",70);
        map.put("teacher",99);
        map.put("tiger",88);
        map.put("thief",77);
        map.put("farmer",66);
        offlineMap.put("djgj100",map);
        Map<String,Integer> map1 = new HashMap<>();
        map1.put("father",67);
        map1.put("mother",87);
        map1.put("dady",56);
        map1.put("mom",55);
        map1.put("grands",85);
        offlineMap.put("hhh200",map1);
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("brother",98);
        map2.put("sister",76);
        map2.put("wolf",64);
        map2.put("cat",83);
        map2.put("dog",93);
        offlineMap.put("yyy222x",map2);
        return offlineMap;
    }
}
