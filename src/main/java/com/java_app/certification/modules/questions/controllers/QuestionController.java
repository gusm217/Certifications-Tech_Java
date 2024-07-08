package com.java_app.certification.modules.questions.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.java_app.certification.modules.questions.dto.AlternativesResultDTO;
import com.java_app.certification.modules.questions.dto.QuestionResultDTO;
import com.java_app.certification.modules.questions.entities.AlternativesEntity;
import com.java_app.certification.modules.questions.entities.QuestionEntity;
import com.java_app.certification.modules.questions.repositories.QuestionRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/technology/{technology}")
	public List<QuestionResultDTO> findByTechnology (@PathVariable String technology) {
		var results = this.questionRepository.findByTechnology(technology);

		var mappedQuestions = results.stream().map(question -> mapQuestionToDTO(question))
			.collect(Collectors.toList());

		return mappedQuestions;
	}

	private static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
		var questionResultDTO = QuestionResultDTO.builder()
		.id(question.getId())
		.technology(question.getTechnology())
		.description(question.getDescription()).build();

		List<AlternativesResultDTO> alternativesResultDTO = question.getAlternatives()
		.stream().map(alternative -> mapAlternativeToDTO(alternative))
		.collect(Collectors.toList());

		questionResultDTO.setAlternatives(alternativesResultDTO);

		return questionResultDTO;
	}

	private static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternative) {
		return AlternativesResultDTO.builder()
		.id(alternative.getId())
		.description(alternative.getDescription()).build();

	}
}
