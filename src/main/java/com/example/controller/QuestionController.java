package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Questions;
import com.example.repository.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping
	public Page<Questions> getQuestions(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}

	@PostMapping
	public Questions createQuestions(@Valid @RequestBody Questions question) {
		return questionRepository.save(question);
	}

	@PutMapping("/{questionId}")
	public Questions updateQuestions(@PathVariable Long questionId, @Valid @RequestBody Questions questionRequest) {

		return questionRepository.findById(questionId).map(question -> {
			question.setTitle(questionRequest.getTitle());
			question.setDescription(questionRequest.getDescription());
			return questionRepository.save(question);
		}).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
	}
}
