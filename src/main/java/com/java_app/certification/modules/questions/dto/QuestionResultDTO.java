package com.java_app.certification.modules.questions.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResultDTO {

	private UUID id;
	private String description;
	private String technology;

	private List<AlternativesResultDTO> alternatives;
}
