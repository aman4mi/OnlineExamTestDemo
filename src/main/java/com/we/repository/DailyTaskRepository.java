package com.we.repository;

import com.we.entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {

    DailyTask findById(long Id);
}
