package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rolerepository extends JpaRepository<Role, Long> {

}