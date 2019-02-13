package com.machineproblem5part2.jonathanwesterfield.mp5part2;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class QuizQuestion implements Serializable
{
    private String topic;
    private String correctAnswer;
    private ArrayList<String> answerChoices;
    private boolean hasBeenUsed;

    public QuizQuestion(String topic, String correctAnswer, ArrayList<String> answerChoices)
    {
        this.topic = topic;
        this.correctAnswer = correctAnswer;
        this.answerChoices = answerChoices;
        this.hasBeenUsed = false;
    }

    public String getTopic()
    {
        return this.topic;
    }

    public String getCorrectAnswer()
    {
        return this.correctAnswer;
    }

    public ArrayList<String> getAnswerChoices()
    {
        return this.answerChoices;
    }

    public boolean hasBeenUsed()
    {
        return this.hasBeenUsed;
    }

    public void setHasBeenUsed()
    {
        this.hasBeenUsed = true;
    }

    public void reset()
    {
        this.hasBeenUsed = false;
    }
}
