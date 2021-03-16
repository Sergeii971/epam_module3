package com.epam.esm.controller;

import com.epam.esm.converter.ToOrderTypeConverter;
import com.epam.esm.converter.ToSortTypeConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.hateoas.GiftCertificateHateoas;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * The type GiftCertificateController.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/giftCertificates")
public class GiftCertificateController {
    private final GiftCertificateService service;
    private final ApplicationContext applicationContext;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, ApplicationContext applicationContext) {
        this.service = giftCertificateService;
        this.applicationContext = applicationContext;
    }

    /**
     * Add gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        return service.add(giftCertificateDto);
    }

    /**
     * Remove gift certificate
     *
     * @param id the certificate id
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGiftCertificate(@PathVariable(value = "id") long id) {
        service.remove(id);
    }

    /**
     * Find gift certificate by id.
     *
     * @return GiftCertificateDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GiftCertificateDto> findGiftCertificateById(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(service.findGiftCertificateById(id), HttpStatus.OK);
    }

    /**
     * update gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto updateGiftCertificate(@PathVariable(value = "id") long id,
            @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setCertificateId(id);
        return service.updateGiftCertificate(giftCertificateDto);
    }

    /**
     * Find all gift certificate by search parameters.
     *
     * @param tagNames the tag name
     * @param description the description
     * @param name the gift certificate name
     * @param sortType the sort type
     * @param orderType the order type
     * @return the list of found gift certificate
     */
    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDto> findGiftCertificatesByParameters(@QueryParam("pageNumber") Integer pageNumber,
                                                                                @QueryParam("size") Integer size,
                                                                                @RequestParam(value = "tagNames", required = false)
                                                                                 List<String> tagNames,
                                                                                @QueryParam(value = "name") String name,
                                                                                @QueryParam(value = "description") String description,
                                                                                @QueryParam(value = "sortType") String sortType,
                                                                                @QueryParam(value = "orderType") String orderType) {
        ToOrderTypeConverter toOrderTypeConverter = applicationContext.getBean(ToOrderTypeConverter.class);
        GiftCertificateQueryParametersDto.OrderType orderType1 = toOrderTypeConverter.convertToOrderType(orderType);
        ToSortTypeConverter toSortTypeConverter = applicationContext.getBean(ToSortTypeConverter.class);
        GiftCertificateQueryParametersDto.SortType sortType1 = toSortTypeConverter.convertToSortType(sortType);
        GiftCertificateQueryParametersDto giftCertificateQueryParametersDto = new GiftCertificateQueryParametersDto(
                tagNames, name, description, sortType1, orderType1);
        List<GiftCertificateDto> certificates = service.findGiftCertificatesByParameters(giftCertificateQueryParametersDto, pageNumber, size);
        GiftCertificateHateoas giftCertificateHateoas = applicationContext.getBean(GiftCertificateHateoas.class);
        return giftCertificateHateoas.addLinksForUpdateGiftCertificate(certificates);
    }
}
