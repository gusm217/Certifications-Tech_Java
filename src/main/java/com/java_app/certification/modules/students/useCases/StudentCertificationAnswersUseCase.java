package com.java_app.certification.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.certification.modules.questions.entities.QuestionEntity;
import com.java_app.certification.modules.questions.repositories.QuestionRepository;
import com.java_app.certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.java_app.certification.modules.students.entities.AnswersCertificationsEntity;
import com.java_app.certification.modules.students.entities.CertificationStudentEntity;
import com.java_app.certification.modules.students.entities.StudentEntity;
import com.java_app.certification.modules.students.repositories.CertificationStudentRepository;
import com.java_app.certification.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CertificationStudentRepository certificationStudentRepository;

	public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) {

		List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
		List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

		AtomicInteger correctAnswers = new AtomicInteger(0);

		dto.getQuestionsAnswers()
				.stream().forEach(questionAnswer -> {
						var question = questionsEntity.stream()
										.filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

						var findCorrectAlternative = question.getAlternatives().stream()
										.filter(alternative -> alternative.isCorrect()).findFirst().get();

						if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
								questionAnswer.setCorrect(true);
								correctAnswers.incrementAndGet();
						} else {
								questionAnswer.setCorrect(false);
						}

						var answersCertificationEntity = AnswersCertificationsEntity.builder()
							.answerID(questionAnswer.getAlternativeID())
							.questionID(questionAnswer.getQuestionID())
							.isCorrect(questionAnswer.isCorrect()).build();

						answersCertifications.add(answersCertificationEntity);
						});

		var student = studentRepository.findByEmail(dto.getEmail());
		UUID studentId;
		if (student.isEmpty()) {
			var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
			studentRepository.save(studentCreated);
			studentId = studentCreated.getId();
		} else {
			studentId = student.get().getId();
		}

		CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
			.technology(dto.getTechnology())
			.studentID(studentId)
			.grade(correctAnswers.get())
			.build();

		var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

		answersCertifications.stream().forEach(answerCertification -> {
				answerCertification.setCertificationID(certificationStudentEntity.getId());
				answerCertification.setCertificationStudentEntity(certificationStudentEntity);
		});

		certificationStudentEntity.setAnswersCertificationEntity(answersCertifications);

		certificationStudentRepository.save(certificationStudentEntity);

		return certificationStudentCreated;
		}
	}
