package com.java_app.certification.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_app.certification.modules.students.entities.CertificationStudentEntity;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID>  {

	@Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
	List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

	@Query("SELECT c FROM certifications c ORDER BY grade DESC LIMIT 10")
	List<CertificationStudentEntity> findTop10ByGradeDesc();
}
