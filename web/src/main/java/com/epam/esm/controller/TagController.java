package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * The type TagController.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Add tag
     *
     * @param tagDto the tag dto
     */
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTag(@RequestBody TagDto tagDto) {
        tagService.add(tagDto);
    }

    /**
     * Find all tags.
     *
     * @return the list of found tags
     */
    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> findAllTags(@RequestParam("pageNumber") int pageNumber,
                                    @RequestParam("size") int size) {
        return tagService.findAll(pageNumber, size);
    }

    /**
     * find tag by id
     *
     * @param id the tag id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagById(@PathVariable(value = "id") long id) {
        return tagService.findTagById(id);
    }
}
