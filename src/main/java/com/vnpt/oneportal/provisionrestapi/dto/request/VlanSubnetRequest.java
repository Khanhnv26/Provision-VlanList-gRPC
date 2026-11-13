package com.vnpt.oneportal.provisionrestapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VlanSubnetRequest {
    private Integer customerId;
    private Integer regionId;
    private Integer region;
    private Integer projectId;
    private Integer vpcId;
    private Integer networkId;
    private String networkCloudId;
    private Integer pageSize;
    private Integer pageNumber;

    public Integer getEffectiveRegionId() {
        return regionId != null ? regionId : region;
    }

    public Integer getEffectiveProjectId() {
        return projectId != null ? projectId : vpcId;
    }
}
