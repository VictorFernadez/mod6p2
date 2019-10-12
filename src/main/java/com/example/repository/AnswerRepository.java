package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	// Keyword
	List<Answer> findByQuestionId(Long questionID);

	@Query("SELECT a FROM Answer a WHERE a.question.id=?1")
	List<Answer> fecthQuestionId(Long questionID);

	@Query(value = "SELECT * FROM answersa WHERE question.id=?1", nativeQuery = true)
	List<Answer> fecthQuestionIdNativeQuery(Long questionID);
}
