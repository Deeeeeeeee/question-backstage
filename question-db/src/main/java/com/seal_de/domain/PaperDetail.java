package com.seal_de.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sealde on 4/25/17.
 */
@Entity
@Table(name = "paper_detail", schema = "question", catalog = "")
public class PaperDetail {
    private String id;
    private String paperId;
    private String questionType;
    private String stem;
    private String examPoint;
    private String answer;
    private String solution;
    private Integer sort;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paper_id")
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "question_type")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "stem")
    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    @Basic
    @Column(name = "examPoint")
    public String getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(String examPoint) {
        this.examPoint = examPoint;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaperDetail that = (PaperDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;
        if (questionType != null ? !questionType.equals(that.questionType) : that.questionType != null) return false;
        if (stem != null ? !stem.equals(that.stem) : that.stem != null) return false;
        if (examPoint != null ? !examPoint.equals(that.examPoint) : that.examPoint != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (solution != null ? !solution.equals(that.solution) : that.solution != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (paperId != null ? paperId.hashCode() : 0);
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        result = 31 * result + (stem != null ? stem.hashCode() : 0);
        result = 31 * result + (examPoint != null ? examPoint.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }
}
