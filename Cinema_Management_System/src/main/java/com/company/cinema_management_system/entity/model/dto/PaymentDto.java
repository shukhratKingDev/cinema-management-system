package com.company.cinema_management_system.entity.model.dto;

import com.company.cinema_management_system.entity.Audience;
import com.company.cinema_management_system.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
  @NotNull
  private Movie movie;
  @NotNull
  private Audience audience;
}
