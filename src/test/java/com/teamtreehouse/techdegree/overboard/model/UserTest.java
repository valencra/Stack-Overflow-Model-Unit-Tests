package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User questioner;
    private User answerer;
    private User voter;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
        questioner = new User(board, "questioner");
        answerer = new User(board, "answerer");
        voter = new User(board, "voter");
    }

    @Test
    public void upvotingQuestionRaisesQuestionerReputationBy5() throws Exception {
        Post question = questioner.askQuestion("What is Java?");

        voter.upVote(question);

        assertEquals(5, questioner.getReputation());
    }

    @Test
    public void upvotingAnswerRaisesAnswererReputationBy10() throws Exception {
        Post question = questioner.askQuestion("What is Java?");
        Post answer = answerer.answerQuestion((Question) question, "Java is a general-purpose programming language.");

        voter.upVote(answer);

        assertEquals(10, answerer.getReputation());
    }

    @Test
    public void acceptingAnswerRaisesAnswererReputationBy15() throws Exception {
        Post question = questioner.askQuestion("What is Java?");
        Post answer = answerer.answerQuestion((Question) question, "Java is a general-purpose programming language.");

        questioner.acceptAnswer((Answer) answer);

        assertEquals(15, answerer.getReputation());

    }
}