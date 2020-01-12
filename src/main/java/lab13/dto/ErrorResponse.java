package lab13.dto;

import lombok.Builder;

@Builder
public class ErrorResponse {
    public int status;
    public String message;
}
