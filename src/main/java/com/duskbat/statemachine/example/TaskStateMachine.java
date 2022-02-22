package com.duskbat.statemachine.example;


import com.duskbat.statemachine.AbsStateMachine;

/**
 * @author muweiye
 */

public class TaskStateMachine extends AbsStateMachine<TaskStatusEnum, TaskEventEnum> {

    public TaskStateMachine(TaskStatusEnum status) {
        super(status);
    }

    @Override
    public void transfer(TaskEventEnum event) {
        TaskStatusEnum state = status.action(event);
        super.setStatus(state);
    }

    public boolean isChanged(TaskStatusEnum status) {
        return !super.status.equals(status);
    }

}
