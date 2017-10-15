package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
    }

    @Test
    public void upvotingQuestionRaisesQuestionerReputationBy5() throws Exception {
        User questioner = new User(board, "questioner");
        Post question = questioner.askQuestion("What is Java?");
        User voter = new User(board, "voter");

        voter.upVote(question);

        assertEquals(5, questioner.getReputation());
    }
}