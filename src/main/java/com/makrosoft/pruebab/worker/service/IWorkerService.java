package com.makrosoft.pruebab.worker.service;

import com.makrosoft.pruebab.utils.response.Response;
import com.makrosoft.pruebab.worker.domain.WorkerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWorkerService {
    /**
     * Service to get all workers
     *
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<List<WorkerDTO>> findAllWorkers();

    /**
     * Service to get a worker
     *
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<WorkerDTO> findWorkerBySec(Integer workerSec);

    /**
     * Service to create a worker
     *
     * @param workerDTO Object to do the save
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<WorkerDTO> createWorker(WorkerDTO workerDTO);

    /**
     * Service to update a worker
     *
     * @param workerSec ID Object to do the search
     * @param workerDTO Object to do the save
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<WorkerDTO> updateWorker(Integer workerSec, WorkerDTO workerDTO);
}
