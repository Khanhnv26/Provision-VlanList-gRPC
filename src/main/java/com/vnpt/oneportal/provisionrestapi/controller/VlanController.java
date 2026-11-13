package com.vnpt.oneportal.provisionrestapi.controller;

import com.vnpt.oneportal.provisionrestapi.dto.request.VlanRequest;
import com.vnpt.oneportal.provisionrestapi.dto.request.VlanSubnetRequest;
import com.vnpt.oneportal.provisionrestapi.dto.response.PageResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanResponse;
import com.vnpt.oneportal.provisionrestapi.dto.response.VlanSubnetResponse;
import com.vnpt.oneportal.provisionrestapi.service.VlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provisions/vlans")
@RequiredArgsConstructor
public class VlanController {

    private final VlanService vlanService;

    //GET /provisions/vlans/vlannetworks?region=5&pageSize=10&pageNumber=1&projectId=864&customerId=153
    @GetMapping("/vlannetworks")
    public ResponseEntity<PageResponse<VlanResponse>> getListVlan(@ModelAttribute VlanRequest request) {
        return ResponseEntity.ok(vlanService.getListVlan(request));
    }


    //GET /provisions/vlans/vlansubnets?pageSize=1000&pageNumber=0&region=5&customerId=153&networkId=771&vpcId=864
    @GetMapping("/vlansubnets")
    public ResponseEntity<PageResponse<VlanSubnetResponse>> getListVlanSubnet(@ModelAttribute VlanSubnetRequest request) {
        return ResponseEntity.ok(vlanService.getListVlanSubnet(request));
    }

}
