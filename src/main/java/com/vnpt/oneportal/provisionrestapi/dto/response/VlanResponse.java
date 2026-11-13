package com.vnpt.oneportal.provisionrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VlanResponse {
    private String subnetCloudId;
    private Integer customerId;
    private String address;
    private Boolean routerExternal;
    private String vpcName;
    private String vlanId;
    private Boolean adminState;
    private Boolean shared;
    private String type;
    private String subnetAddressRequired;
    private String status;
    private Integer vpcId;
    private Integer region;
    private String regionText;
    private List<VlanSubnetResponse> subnets;
    private String vpcShare;
    private Integer parentId;
    private Boolean wanShare;

}
