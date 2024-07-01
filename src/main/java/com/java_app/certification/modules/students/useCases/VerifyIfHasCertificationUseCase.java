package com.java_app.certification.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.certification.modules.students.dto.VerifyIfHasCertificationDTO;
import com.java_app.certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

	@Autowired
	private CertificationStudentRepository certificationStudentRepository;

	public boolean execute(VerifyIfHasCertificationDTO dto) {
		var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
		if (!result.isEmpty()) {
			return true;
		}

		return false;
	}
}
