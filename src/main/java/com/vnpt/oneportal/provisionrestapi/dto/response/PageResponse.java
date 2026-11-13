package com.vnpt.oneportal.provisionrestapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int totalCount;
    private List<T> records;
    private int pageSize;
    private int currentPage;
    private int previousPage;
}
