package com.java_app.certification.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.java_app.certification.modules.students.dto.VerifyIfHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {

	public boolean execute(VerifyIfHasCertificationDTO dto) {
		if (dto.getEmail().equals("teste@teste.com") && dto.getTechnology().equals("JAVA")) {
			return true;
		}

		return false;
	}
}
