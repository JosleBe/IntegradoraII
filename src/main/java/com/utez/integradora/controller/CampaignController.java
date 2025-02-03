package com.utez.integradora.controller;

import com.utez.integradora.entity.CampaignEntity;
import com.utez.integradora.entity.dto.CampaignDto;
import com.utez.integradora.entity.dto.ReqRes;
import com.utez.integradora.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaign")
public class CampaignController {
    private final CampaignService campaignService;
    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignEntity campaignEntity) {
        CampaignDto createdCampaign = campaignService.createCampaign(campaignEntity);
        return ResponseEntity.ok(createdCampaign);
    }

    @GetMapping
    public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
        List<CampaignDto> campaigns = campaignService.getAllCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReqRes> getCampaignById(@PathVariable String id) {
        Optional<CampaignDto> campaign = campaignService.getCampaignById(id);
        if (campaign.isPresent()) {
            return ResponseEntity.ok(new ReqRes("success", campaign.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReqRes> updateCampaign(@PathVariable String id, @RequestBody CampaignEntity campaignEntity) {
        Optional<CampaignDto> updatedCampaign = campaignService.updateCampaign(id, campaignEntity);
        if (updatedCampaign.isPresent()) {
            return ResponseEntity.ok(new ReqRes("success", updatedCampaign.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReqRes> deleteCampaign(@PathVariable String id) {
        campaignService.deleteCampaign(id);
        if(campaignService.getCampaignById(id).isEmpty()){
            return ResponseEntity.ok(new ReqRes(200, "Campaign deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
