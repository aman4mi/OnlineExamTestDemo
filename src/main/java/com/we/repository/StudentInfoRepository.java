package com.we.repository;

import com.we.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by aman.ullah on 3/12/2019.
 */
@Component
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

    StudentInfo findById(long Id);

    @Override
    void delete(StudentInfo studentInfo);

    //    @Query("select t from StudentInfo t where t.Id = :Id")
//    StudentInfo findById(@Param("Id") long Id);
}
