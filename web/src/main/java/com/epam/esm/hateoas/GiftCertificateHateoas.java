package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type GiftCertificateHateoas.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Component
public class GiftCertificateHateoas {
    private final ApplicationContext applicationContext;
    private static final String UPDATE_GIFT_CERTIFICATE = "update gift certificate";

    @Autowired
    public GiftCertificateHateoas(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Add links to gift certificates
     *
     * @param certificates the gift certificates
     * @return the list of gift certificates with links
     */
    public List<GiftCertificateDto> addLinksForUpdateGiftCertificate(List<GiftCertificateDto> certificates) {

        for (GiftCertificateDto certificate : certificates) {
            GiftCertificateService certificateService = applicationContext.getBean(GiftCertificateService.class);

            if (!certificateService.findGiftCertificateById(certificate.getCertificateId()).getIsBought()) {
                String link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                        .updateGiftCertificate(certificate.getCertificateId(), new GiftCertificateDto())).toString();
                certificate.getLinks().put(UPDATE_GIFT_CERTIFICATE, link);
            }
        }
        return certificates;
    }
}
