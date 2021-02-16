package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateHateoas {
    private final ApplicationContext applicationContext;

    @Autowired
    public GiftCertificateHateoas(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<GiftCertificateDto> addLinksForUpdateGiftCertificate(List<GiftCertificateDto> certificates) {

        for (GiftCertificateDto certificate : certificates) {
            GiftCertificateService certificateService = applicationContext.getBean(GiftCertificateService.class);

            if (!certificateService.findGiftCertificateById(certificate.getCertificateId()).getIsBought()) {
                String link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                        .updateGiftCertificate(certificate.getCertificateId(), new GiftCertificateDto())).toString();
                certificate.getLinks().add(link);
            }
        }
        return certificates;
    }
}
