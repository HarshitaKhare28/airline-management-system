
package com.airline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequest {
    @NotBlank(message = "Passenger name required")
    private String passengerName;
    @NotNull(message = "Schedule ID required")
    private Long scheduleId;
}