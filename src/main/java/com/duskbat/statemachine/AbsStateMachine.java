package com.duskbat.statemachine;

import lombok.Getter;
import lombok.Setter;

/**
 * @author muweiye
 */

@Getter
@Setter
public abstract class AbsStateMachine<T extends Status, P extends Event> {

    protected T status;

    protected AbsStateMachine(T status) {
        this.status = status;
    }

    /**
     * 状态转换
     *
     * @param event 触发事件
     */
    public abstract void transfer(P event);

}
