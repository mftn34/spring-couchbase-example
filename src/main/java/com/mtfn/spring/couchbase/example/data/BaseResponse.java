package com.mtfn.spring.couchbase.example.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {

  private int status;

  private String error;

  private String errorCode;

  private String message = "No message available";

  private T data;
}
