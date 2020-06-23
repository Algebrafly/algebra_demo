package com.algebra.demo.util.algorithm;

import cn.hutool.core.collection.CollectionUtil;
import com.algebra.demo.dto.DemoTask;
import com.algebra.demo.dto.DemoUser;
import jodd.util.ObjectUtil;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2020/6/23 11:55
 * @description 需要批量分配订单给人员，尽量保证分配到每个人的订单金额相近。
 *              可以按照金额平均分配，随机分配，指定数量分配。
 */
public class Allot {

    private static final int MAP_INITIAL_CAPACITY = 16;

    /**
     * 按金额分配
     *
     * @param users 人
     * @param tasks 任务
     * @return
     */
    public static Map<String,List<DemoTask>> allotOfAmount(List<DemoUser> users, List<DemoTask> tasks){

        // 保存分配的信息
        Map<String, List<DemoTask>> allot = new ConcurrentHashMap<>(MAP_INITIAL_CAPACITY);

        if(CollectionUtil.isNotEmpty(users) && CollectionUtil.isNotEmpty(tasks)){
            // 深拷贝,在处理过程中会删除已经分配的任务此处方便记录原始数据使用
            List<DemoTask> copyTask = depCopy(tasks);
            if(copyTask == null){
                return null;
            }
            for (int n = 0; n < copyTask.size() + n; n++) {
                if(n % 2 == 0){
                    copyTask = sortTask(copyTask,"desc");
                } else {
                    copyTask = sortTask(copyTask,"asc");
                }
                for (int i = 0; i < users.size(); i++) {
                    if(copyTask.isEmpty()){
                        break;
                    }

                    if(allot.containsKey(users.get(i).getUserId().toString())){
                        List<DemoTask> list = allot.get(users.get(i).getUserId().toString());
                        //每次取排序后的第一条
                        list.add(copyTask.get(0));
                        allot.put(users.get(i).getUserId().toString(), list);
                    } else {
                        List<DemoTask> list = new ArrayList<>();
                        list.add(copyTask.get(0));
                        allot.put(users.get(i).getUserId().toString(), list);
                    }
                    //分配后删除
                    copyTask.remove(copyTask.get(0));
                }
            }
        }
        return allot;
    }

    /**
     * 平均分配&指定数量分配,多余的任务不予分配
     *
     * @param users 人
     * @param tasks 任务
     * @param count 每人指定分配数量
     * @return
     */
    public static Map<String, List<DemoTask>> allotOfAverage(List<DemoUser> users,   List<DemoTask> tasks, Integer count) {
        //随机排序
        Collections.shuffle(users);
        Collections.shuffle(tasks);
        Map<String, List<DemoTask>> allot = new ConcurrentHashMap<>(MAP_INITIAL_CAPACITY);
        if (CollectionUtil.isNotEmpty(users) && CollectionUtil.isNotEmpty(tasks)) {
            for (int i = 0; i < tasks.size(); i++) {

                if (count != null && (i >= count * users.size())) {
                    break;
                }
                int j = i % users.size();
                if (allot.containsKey(users.get(j).getUserId().toString())) {
                    List<DemoTask> list = allot.get(users.get(j).getUserId().toString());
                    list.add(tasks.get(i));
                    allot.put(users.get(j).getUserId().toString(), list);
                } else {
                    List<DemoTask> list = new ArrayList<>();
                    list.add(tasks.get(i));
                    allot.put(users.get(j).getUserId().toString(), list);
                }
            }
        }
        return allot;
    }


    /**
     * 深拷贝
     *
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 排序
     *
     * @param taskList
     * @param sort
     * @return
     */
    private static List<DemoTask> sortTask(List<DemoTask> taskList, String sort) {
        List<DemoTask> newList;
        if ("asc".equalsIgnoreCase(sort)) {
            newList = taskList.stream().sorted(Comparator.comparing(DemoTask::getAmount))
                    .collect(Collectors.toList());
        } else if ("desc".equalsIgnoreCase(sort)) {
            newList = taskList.stream().sorted(Comparator.comparing(DemoTask::getAmount).reversed())
                    .collect(Collectors.toList());
        } else {
            newList = taskList;
        }
        return newList;
    }


    public static void main(String[] args) {

        List<DemoUser> userList = new ArrayList<>();
        DemoUser entity = new DemoUser();
        entity.setUserId(1);
        userList.add(entity);
        DemoUser entity2 = new DemoUser();
        entity2.setUserId(2);
        userList.add(entity2);
        DemoUser entity3 = new DemoUser();
        entity3.setUserId(3);
        userList.add(entity3);

        List<DemoTask> taskList = new ArrayList<>();
        DemoTask apply1 = new DemoTask();
        apply1.setTaskId(1);
        apply1.setAmount(new BigDecimal(1000));
        taskList.add(apply1);
        DemoTask apply2 = new DemoTask();
        apply2.setTaskId(2);
        apply2.setAmount(new BigDecimal(1200));
        taskList.add(apply2);
        DemoTask apply3 = new DemoTask();
        apply3.setTaskId(3);
        apply3.setAmount(new BigDecimal(800));
        taskList.add(apply3);
        DemoTask apply4 = new DemoTask();
        apply4.setTaskId(5);
        apply4.setAmount(new BigDecimal(600));
        taskList.add(apply4);
        DemoTask apply5 = new DemoTask();
        apply5.setTaskId(5);
        apply5.setAmount(new BigDecimal(1600));
        taskList.add(apply5);
        DemoTask apply6 = new DemoTask();
        apply6.setTaskId(6);
        apply6.setAmount(new BigDecimal(2100));
        taskList.add(apply6);
        DemoTask apply7 = new DemoTask();
        apply7.setTaskId(7);
        apply7.setAmount(new BigDecimal(1210));
        taskList.add(apply7);

        DemoTask apply8 = new DemoTask();
        apply8.setTaskId(8);
        apply8.setAmount(new BigDecimal(1120));
        taskList.add(apply8);

        DemoTask apply9 = new DemoTask();
        apply9.setTaskId(9);
        apply9.setAmount(new BigDecimal(1310));
        taskList.add(apply9);

        DemoTask apply10 = new DemoTask();
        apply10.setTaskId(10);
        apply10.setAmount(new BigDecimal(1050));
        taskList.add(apply10);

        DemoTask apply11 = new DemoTask();
        apply11.setTaskId(11);
        apply11.setAmount(new BigDecimal(1500));
        taskList.add(apply11);
        List<DemoTask> copyTask = depCopy(taskList);

        Map<String, List<DemoTask>> map = allotOfAmount(userList,copyTask);

        System.out.println(map);
    }

}
