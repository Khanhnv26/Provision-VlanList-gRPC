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
public class VlanSubnetResponse {
    private Integer id;
    private String name;
    private String subnetCloudId;
    private Integer ipVersion;
    private String networkCloudId;
    private Integer customerId;
    private String mayAoGan;
    private Integer networkId;
    private List<AllocationPoolDto> allocationPools;
    private List<HostRouterDto> hostRouters;
    private Boolean enableDhcp;
    private String gatewayIp;
    private String dnsNameserver;
    private String status;
    private Integer parentId;
    private String cloudId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AllocationPoolDto {
        private String start;
        private String end;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HostRouterDto {
        private String destinationCidr;
        private String nextHop;
    }

}
