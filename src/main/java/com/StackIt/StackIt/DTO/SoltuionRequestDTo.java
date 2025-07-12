package com.StackIt.StackIt.DTO;

public class SoltuionRequestDTo {

    private  String content ;
    private int questionId;

    public SoltuionRequestDTo() {
    }

    public SoltuionRequestDTo(String content, int questionId) {
        this.content = content;
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
}
}