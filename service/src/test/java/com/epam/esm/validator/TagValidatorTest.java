package com.epam.esm.validator;

import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class TagValidatorTest {

    @Test
    public void isNameCorrectPositiveTest() {
        TagValidator validator = new TagValidator();
        Tag tag = new Tag(1, "qqq");
        Optional<String> actual = validator.isTagDataCorrect(tag);
        Optional<String> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test
    public void isNameCorrectNegativeTest() {
        TagValidator validator = new TagValidator();
        Tag tag = new Tag(1, "");
        Optional<String> actual = validator.isTagDataCorrect(tag);
        Optional<String> expected = Optional.empty();
        assertNotEquals(actual, expected);
    }
}