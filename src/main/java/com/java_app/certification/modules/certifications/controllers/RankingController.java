package com.java_app.certification.modules.certifications.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_app.certification.modules.certifications.useCases.Top10RankingUseCase;
import com.java_app.certification.modules.students.entities.CertificationStudentEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/ranking")
public class RankingController {

	@Autowired
	private Top10RankingUseCase top10RankingUseCase;

	@GetMapping("top10")
	public List<CertificationStudentEntity> top10() {
		return top10RankingUseCase.execute();
	}
}
