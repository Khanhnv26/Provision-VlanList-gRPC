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
public class VlanSubnetResponse {
    private Integer id;
    private String name;
    private String subnetCloudId;
    private Integer ipVerison;
    private String networkCloudId;
    private Integer customerId;
    private String mayAoGan;
    private Integer networkId;
    private List<AllocationPoolDto> allocationPools;
    private List<HostRouterDto> hostRouters;
    private Boolean enableDhcp;
    private String networkName;
    private String gatewayIp;
    private String usedBy;
    private String cloudId;
    private Integer vlanId;
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllocationPoolDto {
        private String start;
        private String end;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HostRouterDto {
        private String destinationCidr;
        private String nextHop;
    }
}
