package com.vnpt.oneportal.provisionrestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VlanRequest {
    private Integer regionId;
    private Integer region;
    private Integer projectId;
    private Integer customerId;
    private Integer pageSize;
    private Integer pageNumber;

    public Integer getEffectiveRegionId() {
        return regionId != null ? regionId : region;
    }
}
