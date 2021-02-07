package com.epam.esm.service.impl;

import com.epam.esm.configuration.ServiceConfiguration;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = { ServiceConfiguration.class, TagServiceImpl.class, TagDaoImpl.class})
class TagServiceImplTest {

    @Autowired
    @InjectMocks
    private TagServiceImpl tagService;

    @Autowired
    @Mock
    private TagDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllPositiveTest() {
        long tagId = 1;
        String name = "qqq";
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(tagId, name));
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(tagId, name));
        Mockito.when(dao.findAll()).thenReturn(tags);
        assertEquals(tagService.findAll(), expected);
    }

    @Test
    public void findAllNegativeTest() {
        long tagId = 1;
        String name = "qqq";
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(tagId, name));
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(tagId, "qqqq"));
        Mockito.when(dao.findAll()).thenReturn(tags);
        assertNotEquals(tagService.findAll(), expected);
    }

    @Test
    public void findTagByIdPositiveTest() {
        long tagId = 1;
        String name = "qqq";
        Tag tag = new Tag(tagId, name);
        TagDto expected = new TagDto(tagId, name);
        Mockito.when(dao.findById(tagId)).thenReturn(Optional.of(tag));
        assertEquals(tagService.findTagById(tagId), expected);
    }

    @Test
    public void findTagByIdNegativeTest() {
        long tagId = 1;
        String name = "qqq";
        Tag tag = new Tag(tagId, name);
        TagDto expected = new TagDto(tagId, "qqqq");
        Mockito.when(dao.findById(tagId)).thenReturn(Optional.of(tag));
        assertNotEquals(tagService.findTagById(tagId), expected);
    }

    @Test
    public void findTagByIdExceptionTest() {
        Mockito.doReturn(Optional.empty()).when(dao).findById(1);
        assertThrows(ResourceNotFoundException.class, () -> tagService.findTagById(1));
    }

    @Test
    public void findTagsByGiftCertificateIdPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(certificateId, name));
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(certificateId, name));
        Mockito.when(dao.findByGiftCertificateId(certificateId)).thenReturn(tags);
        assertEquals(tagService.findTagsByGiftCertificateId(certificateId), expected);
    }

    @Test
    public void findTagsByGiftCertificateIdNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(certificateId, name));
        List<TagDto> expected = new ArrayList<>();
        expected.add(new TagDto(certificateId, "qqqq"));
        Mockito.when(dao.findByGiftCertificateId(certificateId)).thenReturn(tags);
        assertNotEquals(tagService.findTagsByGiftCertificateId(certificateId), expected);
    }
}