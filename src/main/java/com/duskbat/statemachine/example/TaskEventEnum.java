package com.duskbat.statemachine.example;


import com.duskbat.statemachine.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作事件
 *
 * @author muweiye
 */

@Getter
@AllArgsConstructor
public enum TaskEventEnum implements Event {

    /**
     * 查看
     */
    VIEW,
    /**
     * 停止
     */
    STOP,
    /**
     * 撤回
     */
    WITHDRAW,
    /**
     * 编辑
     */
    EDIT,
    /**
     * 进行
     */
    PROGRESS,
    /**
     * 下发
     */
    ISSUE;
}
