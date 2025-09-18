package com.javaTest.deploy.repository;

import com.javaTest.deploy.entity.Deploy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeployRepository extends JpaRepository<Deploy, Long> {
    
}
