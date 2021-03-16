package com.epam.esm.exception;

/**
 * The type ExceptionMessageKey.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
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
    public static final String INSUFFICIENT_FUNDS_IN_ACCOUNT = "order.insufficientFundsInAccount";
    public static final String NO_JWT_TOKEN_IN_HEADER = "no_jwt_token_in_header";
    public static final String INVALID_JWT_SIGNATURE = "invalid_JWT_signature";
    public static final String INCORRECT_JWT_TOKEN = "invalid_JWT_token";
    public static final String EXPIRED_JWT_TOKEN = "expired_JWT_token";
    public static final String UNSUPPORTED_JWT_TOKEN = "unsupported_JWT_token";
    public static final String JWT_CLAIMS_STRING_IS_EMPTY = "JWT_claims_string_is_empty";

    private ExceptionMessageKey() {
    }
}
