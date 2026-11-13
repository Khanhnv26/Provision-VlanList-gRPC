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
public class VlanResponse {
    private String subnetCloudId;
    private Integer customerId;
    private String address;
    private Boolean routerExternal;
    private String vpcName;
    private Integer id;
    private String cloudId;
    private String name;
    private Integer vlanId;
    private Boolean adminState;
    private Boolean shared;
    private String type;
    private String subnetAddressRequired;
    private String status;
    private Integer vpcId;
    private Integer region;
    private String regionText;
    private List<SubnetDto> subnets;
    private String vpcShare;
    private Integer parentId;
    private Boolean wanShare;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubnetDto {
        private Integer id;
        private String name;
        private String networkAddress;
        private Integer ipVersion;
        private String gatewayIp;
        private Boolean enableDHCP;
        private String dnsNameServer;
        private String cloudId;
        private String status;
        private Integer networkId;
        private String networkCloudId;
        private String usedBy;
        private Integer parentId;
        private List<VlanSubnetResponse.AllocationPoolDto> allocationPools;
        private List<VlanSubnetResponse.HostRouterDto> hostRouters;
    }
}
