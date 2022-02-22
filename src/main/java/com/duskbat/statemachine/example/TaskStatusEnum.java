package com.duskbat.statemachine.example;

import com.duskbat.statemachine.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务单状态枚举
 *
 * @author muweiye
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum implements Status {

    /**
     * 初始化
     */
    INIT((byte) 0, "初始化"),

    /**
     * 待下发：已生成任务单但是未进行下发
     */
    TO_BE_ISSUED((byte) 10, "待下发") {
        @Override
        public TaskStatusEnum action(TaskEventEnum event) {
            return TaskEventEnum.ISSUE.equals(event) ? ISSUED : this;
        }

        @Override
        public boolean editAble() {
            return true;
        }
    },

    /**
     * 已下发：已下发但未被领取的任务单
     */
    ISSUED((byte) 20, "已下发") {
        @Override
        public TaskStatusEnum action(TaskEventEnum event) {
            return switch (event) {
                // 撤回: ==>待下发
                case WITHDRAW -> TO_BE_ISSUED;
                // 进行: ==>进行中
                case PROGRESS -> IN_PROGRESS;
                default -> this;
            };
        }
    },

    /**
     * 进行中：进行中的任务单
     */
    IN_PROGRESS((byte) 30, "进行中") {
        @Override
        public TaskStatusEnum action(TaskEventEnum event) {
            return TaskEventEnum.STOP.equals(event) ? FINISH : this;
        }
    },

    /**
     * 已完成：任务单已经被完成
     */
    FINISH((byte) 40, "已完成"),

    ;

    /**
     * 状态码
     */
    private byte status;

    /**
     * 描述
     */
    private String desc;


    /**
     * 响应事件
     */
    public TaskStatusEnum action(TaskEventEnum event) {
        return this;
    }

    /**
     * 是否可编辑
     */
    public boolean editAble() {
        return false;
    }


    public final static Map<Byte, TaskStatusEnum> MAP = new HashMap<>(4);

    static {
        Arrays.stream(TaskStatusEnum.values())
                .forEach(o ->
                        MAP.put(o.getStatus(), o)
                );
    }

}
