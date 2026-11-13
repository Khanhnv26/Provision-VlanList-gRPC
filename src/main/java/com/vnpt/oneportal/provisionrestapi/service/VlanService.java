package com.vnpt.oneportal.provisionrestapi.service;

import com.vnpt.oneportal.provisionrestapi.dto.request.VlanRequest;
import com.vnpt.oneportal.provisionrestapi.dto.request.VlanSubnetRequest;
import com.vnpt.oneportal.provisionrestapi.dto.response.PageResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanSubnetResponse;

public interface VlanService {
    PageResponse<VlanResponse> getListVlan(VlanRequest request);
    PageResponse<VlanSubnetResponse> getListVlanSubnet(VlanSubnetRequest request);
}
