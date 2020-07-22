package com.company.taxfiler.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "other_income_informaton")
public class OtherIncomeInformatonEntity {

	private long id;
	private String question;
	private String answer;
	private String comments;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
