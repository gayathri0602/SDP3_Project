package com.klef.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob, String> {

}