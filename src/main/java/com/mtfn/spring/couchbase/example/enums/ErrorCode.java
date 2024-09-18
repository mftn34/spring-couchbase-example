package com.mtfn.spring.couchbase.example.enums;

public enum ErrorCode {
  METHOD_ARGUMENT_NOT_VALID_EXCEPTION("0001", "MethodArgumentNotValidException"),
  MISSING_SERVLET_REQUEST_PART_EXCEPTION("0004", "MissingServletRequestPartException"),
  MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("0005", "MissingServletRequestParameterException"),
  NO_HANDLER_FOUND_EXCEPTION("0006", "NoHandlerFoundException"),
  HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("0007", "HttpRequestMethodNotSupportedException"),
  HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("0008", "HttpMediaTypeNotSupportedException"),
  MISSING_PATH_VARIABLE_EXCEPTION("0009", "MissingPathVariableException"),
  HTTP_MESSAGE_NOT_READABLE_EXCEPTION("0010", "HttpMessageNotReadableException"),
  METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION("0011", "MethodArgumentTypeMismatchException"),
  CONSTRAINT_VIOLATION_EXCEPTION("0012", "ConstraintViolationException"),
  ILLEGAL_ARGUMENT_EXCEPTION("0014", "Inappropriate argument"), JSON_MAPPING_EXCEPTION("0015", "JsonMappingException"),
  CONVERSION_FAILED_EXCEPTION("0017", "ConversionFailedException"), JSON_PARSE_EXCEPTION("0018", "JsonParseException"),
  STORE_NOT_FOUND_EXCEPTION("1001", "Store Not found");

  private static final String ERROR_PREFIX = "EXP";

  private final String code;

  private final String name;

  ErrorCode(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return String.format("%s-%s", ERROR_PREFIX, code);
  }

  public String getName() {
    return name;
  }
}
