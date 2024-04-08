package com.makrosoft.pruebab.worker.service;


import com.makrosoft.pruebab.support.domain.SupportDTO;
import com.makrosoft.pruebab.support.model.Support;
import com.makrosoft.pruebab.support.respository.ISupportRepository;
import com.makrosoft.pruebab.utils.exception.BusinessRuleException;
import com.makrosoft.pruebab.utils.response.Response;
import com.makrosoft.pruebab.utils.response.handler.ResponseHandler;
import com.makrosoft.pruebab.worker.domain.WorkerDTO;
import com.makrosoft.pruebab.worker.model.Worker;
import com.makrosoft.pruebab.worker.respository.IWorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService implements IWorkerService{
    private final ISupportRepository iSupportRepository;
    private final IWorkerRepository iWorkerRepository;
    private final ModelMapper modelMapper;

    public WorkerService(ISupportRepository iSupportRepository, IWorkerRepository iWorkerRepository, ModelMapper modelMapper) {
        this.iSupportRepository = iSupportRepository;
        this.iWorkerRepository = iWorkerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<WorkerDTO>> findAllWorkers() {
        List<Worker> workerList = this.iWorkerRepository.findAll();
        if(workerList.isEmpty()){
            throw new BusinessRuleException("workers.not.found");
        }

        List<WorkerDTO> workerListDTO = workerList.stream().map(worker -> modelMapper.map(worker, WorkerDTO.class)).collect(Collectors.toList());

        return new ResponseHandler<>(200, "Se han encontrado los trabajadores con exito", "http://localhost:8081/worker/find_all_workers", workerListDTO).getResponse();
    }

    @Override
    public Response<WorkerDTO> findWorkerBySec(Integer workerSec) {
        Optional<Worker> workerFound = this.iWorkerRepository.findById(workerSec);
        if(workerFound.isEmpty()){
            throw new BusinessRuleException("worker.not.found");
        }

        WorkerDTO workerDTO = modelMapper.map(workerFound.get(),WorkerDTO.class);

        return new ResponseHandler<>(200, "Se ha encontrado el trabajador con exito", "http://localhost:8081/worker/find_worker_by_sec/{workerSec}", workerDTO).getResponse();
    }

    @Override
    public Response<WorkerDTO> createWorker(WorkerDTO workerDTO) {
        Worker workerEntity = this.modelMapper.map(workerDTO, Worker.class);
        WorkerDTO savedWorkerDTO = this.modelMapper.map(this.iWorkerRepository.save(workerEntity), WorkerDTO.class);

        return new ResponseHandler<>(200, "Trabajador creado con éxito", "http://localhost:8081/worker/create_worker", savedWorkerDTO).getResponse();
    }

    @Override
    public Response<WorkerDTO> updateWorker(Integer workerSec, WorkerDTO workerDTO) {
        Optional<Worker> optionalWorker = this.iWorkerRepository.findById(workerSec);
        if (optionalWorker.isEmpty())
            throw new BusinessRuleException("worker.request.not.update");

        Worker workerEntity = optionalWorker.get();
        Worker workerEntityUpdate = this.modelMapper.map(workerDTO, Worker.class);
        workerEntity.setName(workerEntityUpdate.getName());
        workerEntity.setIdentification(workerEntityUpdate.getIdentification());
        WorkerDTO updateWorkerDTO = this.modelMapper.map(iWorkerRepository.save(workerEntity), WorkerDTO.class);

        return new ResponseHandler<>(200, "Trabajador actualizado con éxito", "http://localhost:8081/worker/update_worker/{workerSec}", updateWorkerDTO).getResponse();
    }
}
