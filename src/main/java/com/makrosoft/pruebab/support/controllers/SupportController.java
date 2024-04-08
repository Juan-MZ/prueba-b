package com.makrosoft.pruebab.support.controllers;

import com.makrosoft.pruebab.support.domain.SupportDTO;
import com.makrosoft.pruebab.support.service.ISupportService;
import com.makrosoft.pruebab.utils.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupportController {
    private final ISupportService iSupportService;

    public SupportController(ISupportService iSupportService) {
        this.iSupportService = iSupportService;
    }

    @GetMapping("/find_all_supports")
    public Response<List<SupportDTO>> findAllSupports() {
        return iSupportService.findAllSupports();
    }

    @GetMapping("/find_support_by_sec/{supportSec}")
    public Response<SupportDTO> findSupportBySec(@PathVariable final Integer supportSec) {
        return iSupportService.findSupportBySec(supportSec);
    }

    @PostMapping("/create_support")
    public Response<SupportDTO> createSupport(@RequestBody final SupportDTO supportDTO) {
        return iSupportService.createSupport(supportDTO);
    }

    @PatchMapping("")
    public Response<SupportDTO> updateSupport(@PathVariable final Integer supportSec, @RequestBody final SupportDTO supportDTO) {
        return iSupportService.updateSupport(supportSec, supportDTO);
    }

    @GetMapping("/find_all_supports_by_worker/{workerSec}")
    public Response<List<SupportDTO>> getSupportsByWorkerSec(@PathVariable final Integer workerSec) {
        return iSupportService.getSupportsByWorkerSec(workerSec);
    }
}
