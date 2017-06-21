package com.a46inch.a46inch.Classes;

/**
 * Created by ar265 on 6/21/2017.
 */

public class FAQ {
    public String question;
    public String answer;
    public FAQ(){
        //empty constructor
    }
    public FAQ(String Ques,String Ans){
        this.question = Ques;
        this.answer = Ans;
    }
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

}
