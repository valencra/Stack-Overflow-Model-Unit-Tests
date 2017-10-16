package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User questioner;
    private User answerer;
    private User voter;
    private Post question;
    private Post answer;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
        questioner = new User(board, "questioner");
        answerer = new User(board, "answerer");
        voter = new User(board, "voter");
        question = questioner.askQuestion("What is Java?");
        answer = answerer.answerQuestion((Question) question, "Java is a general-purpose programming language.");
    }

    @Test
    public void upvotingQuestionRaisesQuestionerReputationBy5() throws Exception {
        voter.upVote(question);

        assertEquals(5, questioner.getReputation());
    }

    @Test
    public void upvotingAnswerRaisesAnswererReputationBy10() throws Exception {
        voter.upVote(answer);

        assertEquals(10, answerer.getReputation());
    }

    @Test
    public void downvotingAnswerDecreasesAnswererReputationBy1() throws Exception {
        voter.downVote(answer);

        assertEquals(-1, answerer.getReputation());
    }

    @Test
    public void acceptingAnswerRaisesAnswererReputationBy15() throws Exception {
        questioner.acceptAnswer((Answer) answer);

        assertEquals(15, answerer.getReputation());

    }

    @Test
    public void upvotingIsNotAllowedOnQuestionByAuthor() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.upVote(question);
    }

    @Test
    public void downvotingIsNotAllowedOnQuestionByAuthor() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        questioner.downVote(question);
    }

    @Test
    public void upvotingIsNotAllowedOnAnswerByAuthor() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.upVote(answer);
    }

    @Test
    public void downvotingIsNotAllowedOnAnswerByAuthor() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        answerer.downVote(answer);
    }

    @Test
    public void acceptingAnswerOnlyPermittedToQuestioner() throws Exception {
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage(String.format("Only %s can accept this answer as it is their question", questioner.getName()));

        answerer.acceptAnswer((Answer) answer);
    }
}