package com.makrosoft.pruebab.worker.controllers;

import com.makrosoft.pruebab.utils.response.Response;
import com.makrosoft.pruebab.worker.domain.WorkerDTO;
import com.makrosoft.pruebab.worker.service.IWorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkerController {
    private final IWorkerService iWorkerService;

    public WorkerController(IWorkerService iWorkerService) {
        this.iWorkerService = iWorkerService;
    }

    @GetMapping("/find_all_workers")
    public Response<List<WorkerDTO>> findAllWorkers() {
        return iWorkerService.findAllWorkers();
    }

    @GetMapping("/find_worker_by_sec/{workerSec}")
    public Response<WorkerDTO> findWorkerBySec(@PathVariable final Integer workerSec) {
        return iWorkerService.findWorkerBySec(workerSec);
    }

    @PostMapping("/create_worker")
    public Response<WorkerDTO> createWorker(@RequestBody final WorkerDTO workerDTO) {
        return iWorkerService.createWorker(workerDTO);
    }

    @PatchMapping("/update_worker/{workerSec}")
    public Response<WorkerDTO> updateWorker(@PathVariable final Integer workerSec, @RequestBody final WorkerDTO workerDTO) {
        return iWorkerService.updateWorker(workerSec, workerDTO);
    }
}
