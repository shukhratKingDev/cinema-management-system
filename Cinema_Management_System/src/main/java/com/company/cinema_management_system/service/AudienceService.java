package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.Audience;
import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;

import java.util.List;
import java.util.Optional;

public interface AudienceService {
    Response addAudience(AudienceDto audienceDto);
    Response upDateAudience(AudienceDto audienceDto,Integer id);
    List<Audience> getList();
    Optional<Audience> getById(Integer id);
    Response deleteById(Integer id);
}
