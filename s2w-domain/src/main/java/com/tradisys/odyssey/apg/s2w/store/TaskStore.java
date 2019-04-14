package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import java.util.List;

public interface TaskStore extends BaseStore<Task> {

    void saveAssignment(long taskId, long userId);

    List<Task> findAllAssignedTo(long userId);

    List<Task> findAllCreatedBy(Organization org);
}
