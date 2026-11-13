package com.vnpt.oneportal.provisionrestapi.service.impl;

import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import com.vnpt.oneportal.provisionrestapi.dto.request.VlanRequest;
import com.vnpt.oneportal.provisionrestapi.dto.request.VlanSubnetRequest;
import com.vnpt.oneportal.provisionrestapi.dto.response.PageResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanSubnetResponse;
import com.vnpt.oneportal.provisionrestapi.service.VlanService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import provision.grpc.*;

import java.util.List;

@Service
public class VlanServiceImpl implements VlanService {

    @GrpcClient("provision-service")
    private ProvisionsGrpc.ProvisionsBlockingStub provisionStub;

    @Override
    public PageResponse<VlanResponse> getListVlan(VlanRequest request) {
        GetListVlanRequest.Builder requestBuilder = GetListVlanRequest.newBuilder();

        if (request.getCustomerId() != null) {
            requestBuilder.setCustomerId(request.getCustomerId());
        }

        Integer region = request.getEffectiveRegionId();
        if (region != null) {
            requestBuilder.setRegionId(Int32Value.of(region));
        }

        if (request.getProjectId() != null) {
            requestBuilder.setProjectId(Int32Value.of(request.getProjectId()));
        }

        GetListVlanResponse grpcResponse = provisionStub.getListVlan(requestBuilder.build());
        List<VlanResponse> list = grpcResponse.getVlansList().stream()
                .map(this::mapToVlanResponse)
                .toList();

        return buildPageResponse(list, request.getPageSize(), request.getPageNumber(), 10, 1);
    }

    @Override
    public PageResponse<VlanSubnetResponse> getListVlanSubnet(VlanSubnetRequest request) {
        GetListVlanSubnetRequest.Builder requestBuilder = GetListVlanSubnetRequest.newBuilder();

        Integer region = request.getEffectiveRegionId();
        if (region != null) {
            requestBuilder.setRegionId(Int32Value.of(region));
        }

        Integer projectId = request.getEffectiveProjectId();
        if (projectId != null) {
            requestBuilder.setProjectId(Int32Value.of(projectId));
        }

        if (request.getCustomerId() != null) {
            requestBuilder.setCustomerId(Int32Value.of(request.getCustomerId()));
        }

        if (request.getNetworkId() != null) {
            requestBuilder.setNetworkId(Int32Value.of(request.getNetworkId()));
        }

        if (request.getNetworkCloudId() != null && !request.getNetworkCloudId().isBlank()) {
            requestBuilder.setNetworkCloudId(StringValue.of(request.getNetworkCloudId()));
        }

        GetListVlanSubnetResponse grpcResponse = provisionStub.getListVlanSubnet(requestBuilder.build());
        List<VlanSubnetResponse> list = grpcResponse.getVlansubnetsList().stream()
                .map(proto -> mapToVlanSubnetFlat(proto, projectId))
                .toList();

        return buildPageResponse(list, request.getPageSize(), request.getPageNumber(), 1000, 0);
    }

    private <T> PageResponse<T> buildPageResponse(
            List<T> records,
            Integer requestedPageSize,
            Integer requestedPageNumber,
            int defaultPageSize,
            int defaultPageNumber) {
        int pageSize = requestedPageSize != null ? requestedPageSize : defaultPageSize;
        int currentPage = requestedPageNumber != null ? requestedPageNumber : defaultPageNumber;
        int previousPage = Math.max(0, currentPage - 1);

        return PageResponse.<T>builder()
                .totalCount(records.size())
                .records(records)
                .pageSize(pageSize)
                .currentPage(currentPage)
                .previousPage(previousPage)
                .build();
    }

    private VlanResponse mapToVlanResponse(GrpcVlanInfo proto) {
        return VlanResponse.builder()
                .subnetCloudId(proto.hasSubnetCloudId() ? proto.getSubnetCloudId().getValue() : null)
                .customerId(proto.hasCustomerId() ? proto.getCustomerId().getValue() : null)
                .address(proto.hasAddress() ? proto.getAddress().getValue() : null)
                .routerExternal(proto.hasRouterExternal() ? proto.getRouterExternal().getValue() : null)
                .vpcName(proto.hasVpcName() ? proto.getVpcName().getValue() : null)
                .id(proto.getId())
                .cloudId(proto.hasCloudId() ? proto.getCloudId().getValue() : null)
                .name(proto.hasName() ? proto.getName().getValue() : null)
                .vlanId(proto.hasVlanId() ? proto.getVlanId().getValue() : null)
                .adminState(proto.hasAdminState() ? proto.getAdminState().getValue() : null)
                .shared(proto.hasShared() ? proto.getShared().getValue() : null)
                .type(proto.hasType() ? proto.getType().getValue() : null)
                .subnetAddressRequired(proto.hasSubnetAddressRequired() ? proto.getSubnetAddressRequired().getValue() : null)
                .status(proto.hasStatus() ? proto.getStatus().getValue() : null)
                .vpcId(proto.hasVpcId() ? proto.getVpcId().getValue() : null)
                .region(proto.hasRegion() ? proto.getRegion().getValue() : null)
                .regionText(proto.hasRegionText() ? proto.getRegionText().getValue() : null)
                .subnets(proto.getSubnetsList().stream().map(this::mapToVlanSubnetChild).toList())
                .vpcShare(proto.hasVpcShare() ? proto.getVpcShare().getValue() : null)
                .parentId(proto.hasParentId() ? proto.getParentId().getValue() : null)
                .wanShare(proto.hasWanShare() ? proto.getWanShare().getValue() : null)
                .build();
    }

    private VlanResponse.SubnetDto mapToVlanSubnetChild(GrpcVlanSubnetInfo proto) {
        return VlanResponse.SubnetDto.builder()
                .id(proto.hasId() ? proto.getId().getValue() : null)
                .name(proto.hasName() ? proto.getName().getValue() : null)
                .networkAddress(proto.hasNetworkAddress() ? proto.getNetworkAddress().getValue() : null)
                .ipVersion(proto.hasIPVerison() ? proto.getIPVerison().getValue() : null)
                .gatewayIp(proto.hasGatewayIp() ? proto.getGatewayIp().getValue() : null)
                .enableDHCP(proto.hasEnableDhcp() ? proto.getEnableDhcp().getValue() : null)
                .dnsNameServer(proto.hasDnsNameServer() ? proto.getDnsNameServer().getValue() : null)
                .cloudId(proto.hasCloudId() ? proto.getCloudId().getValue() : null)
                .status(proto.hasStatus() ? proto.getStatus().getValue() : null)
                .networkId(proto.hasNetworkId() ? proto.getNetworkId().getValue() : null)
                .networkCloudId(proto.hasNetworkCloudId() ? proto.getNetworkCloudId().getValue() : null)
                .usedBy(proto.hasUsedBy() ? proto.getUsedBy().getValue() : null)
                .parentId(proto.hasParentId() ? proto.getParentId().getValue() : null)
                .allocationPools(mapAllocationPools(proto.getAllocationPoolsList()))
                .hostRouters(mapHostRouters(proto.getHostRoutersList()))
                .build();
    }

    private VlanSubnetResponse mapToVlanSubnetFlat(GrpcVlanSubnetInfo proto, Integer vpcId) {
        String cloudId = proto.hasCloudId() ? proto.getCloudId().getValue()
                : (proto.hasSubnetCloudId() ? proto.getSubnetCloudId().getValue() : null);
        Integer networkId = proto.hasNetworkId() ? proto.getNetworkId().getValue() : null;

        return VlanSubnetResponse.builder()
                .id(proto.hasId() ? proto.getId().getValue() : null)
                .name(proto.hasName() ? proto.getName().getValue() : null)
                .subnetCloudId(proto.hasSubnetCloudId() ? proto.getSubnetCloudId().getValue() : null)
                .ipVerison(proto.hasIPVerison() ? proto.getIPVerison().getValue() : null)
                .networkCloudId(proto.hasNetworkCloudId() ? proto.getNetworkCloudId().getValue() : null)
                .customerId(proto.hasCustomerId() ? proto.getCustomerId().getValue() : null)
                .mayAoGan(proto.hasMayAoGan() ? proto.getMayAoGan().getValue() : null)
                .networkId(networkId)
                .allocationPools(mapAllocationPools(proto.getAllocationPoolsList()))
                .hostRouters(mapHostRouters(proto.getHostRoutersList()))
                .enableDhcp(proto.hasEnableDhcp() ? proto.getEnableDhcp().getValue() : null)
                .networkName(proto.hasNetworkName() ? proto.getNetworkName().getValue() : null)
                .gatewayIp(proto.hasGatewayIp() ? proto.getGatewayIp().getValue() : null)
                .usedBy(proto.hasUsedBy() ? proto.getUsedBy().getValue() : null)
                .cloudId(cloudId)
                .vlanId(networkId)
                .adminState(null)
                .shared(null)
                .type(null)
                .subnetAddressRequired(proto.hasNetworkAddress() ? proto.getNetworkAddress().getValue() : null)
                .status(proto.hasStatus() ? proto.getStatus().getValue() : null)
                .vpcId(vpcId)
                .region(null)
                .regionText(null)
                .subnets(null)
                .vpcShare(null)
                .parentId(proto.hasParentId() ? proto.getParentId().getValue() : null)
                .wanShare(null)
                .build();
    }

    private List<VlanSubnetResponse.AllocationPoolDto> mapAllocationPools(List<GrpcVpcSubnetAllocationPool> pools) {
        if (pools.isEmpty()) {
            return null;
        }
        return pools.stream()
                .map(p -> VlanSubnetResponse.AllocationPoolDto.builder()
                        .start(p.hasStart() ? p.getStart().getValue() : null)
                        .end(p.hasEnd() ? p.getEnd().getValue() : null)
                        .build())
                .toList();
    }

    private List<VlanSubnetResponse.HostRouterDto> mapHostRouters(List<GrpcVpcRouteConfig> routes) {
        if (routes.isEmpty()) {
            return null;
        }
        return routes.stream()
                .map(r -> VlanSubnetResponse.HostRouterDto.builder()
                        .destinationCidr(r.hasDestinationCidr() ? r.getDestinationCidr().getValue() : null)
                        .nextHop(r.hasNextHop() ? r.getNextHop().getValue() : null)
                        .build())
                .toList();
    }
}
