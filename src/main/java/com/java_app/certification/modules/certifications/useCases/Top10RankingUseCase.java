package com.java_app.certification.modules.certifications.useCases;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.certification.modules.students.entities.CertificationStudentEntity;
import com.java_app.certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

	@Autowired
	private CertificationStudentRepository certificationStudentRepository;

	public List<CertificationStudentEntity> execute() {
		return certificationStudentRepository.findTop10ByGradeDesc();
	}
}
