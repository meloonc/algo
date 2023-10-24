package org.melon.tree;

import java.util.List;

/**
 * 一个公司的上下节关系是一颗多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理：
 * 一个员工的直接上级如果到场，这个员工肯定不会来。
 * 每个员工都有一个活跃度的值，决定谁来你会给这个员工发邀请函，怎么让舞会的气氛最活跃？
 * 返回最大的活跃值。
 */
public class MaxHappy {
    static class Employee {
        List<Employee> children;
        Integer happy;
        public Employee(List<Employee> children, Integer happy) {
            this.children = children;
            this.happy = happy;
        }
    }


    public Integer maxHappy(Employee e) {
        Info info = process(e);
        return Math.max(info.present, info.absent);
    }

    private Info process(Employee e){
        if(e == null) {
            return new Info(0,0);
        }
        int present = e.happy;
        int absent = 0;
        for (Employee child : e.children) {
            Info info = process(child);
            present += info.absent;
            // 下级可来可不来，取最大值
            absent += Math.max(info.absent, info.present);
        }
        return new Info(present, absent);

    }

    static class Info {
        // 出席的最大活跃值
        private Integer present;
        // 缺席的最大活跃值
        private Integer absent;

        public Info(Integer present, Integer absent) {
            this.present = present;
            this.absent = absent;
        }
    }


}
