package com.epam.esm.exception;

public class ExceptionMessageKey {
    public static final String GIFT_CERTIFICATE_NOT_FOUND_BY_ID = "giftCertificate.notFoundById";
    public static final String USER_NOT_FOUND_BY_ID = "user.notFoundById";
    public static final String TAG_NOT_FOUND_BY_ID = "tag.notFoundById";
    public static final String LOGIN_ALREADY_EXIST = "user.loginAlreadyExist";
    public static final String CERTIFICATE_ALREADY_BOUGHT = "order.certificateIsAlreadyBought";
    public static final String INCORRECT_PAGINATION_DATA = "pagination_exception";
    public static final String ORDER_NOT_FOUND_BY_ID = "order.notFoundById";
    public static final String GIFT_CERTIFICATE_NOT_FOUND = "giftCertificate.notFound";
    public static final String TAG_NAME_ALREADY_EXIST = "tag.nameAlreadyExist";
    public static final String Tag_USE_BY_PURCHASED_CERTIFICATE = "order.tagIsUseByPurchasedCertificate";

    private ExceptionMessageKey() {
    }
}
