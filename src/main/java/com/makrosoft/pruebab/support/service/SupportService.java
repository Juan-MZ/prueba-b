package com.makrosoft.pruebab.support.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupportService implements ISupportService{
    private final ISupportRepository iSupportRepository;
    private final IWorkerRepository iWorkerRepository;
    private final ModelMapper modelMapper;

    public SupportService(ISupportRepository iSupportRepository, IWorkerRepository iWorkerRepository, ModelMapper modelMapper) {
        this.iSupportRepository = iSupportRepository;
        this.iWorkerRepository = iWorkerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<SupportDTO>> findAllSupports() {
        List<Support> supportList = this.iSupportRepository.findAll();
        if(supportList.isEmpty()){
            throw new BusinessRuleException("supports.not.found");
        }

        List<SupportDTO> supportListDTO = supportList.stream().map(support -> modelMapper.map(support, SupportDTO.class)).collect(Collectors.toList());

        return new ResponseHandler<>(200, "Se han encontrado los soportes con exito", "http://localhost:8081/support/find_all_supports", supportListDTO).getResponse();

    }

    @Override
    public Response<SupportDTO> findSupportBySec(Integer supportSec) {
        Optional<Support> supportFound = this.iSupportRepository.findById(supportSec);
        if(supportFound.isEmpty()){
            throw new BusinessRuleException("support.not.found");
        }

        SupportDTO supportDTO = modelMapper.map(supportFound.get(),SupportDTO.class);

        return new ResponseHandler<>(200, "Se ha encontrado el soporte con exito", "http://localhost:8081/support/find_support_by_sec/{supportSec}", supportDTO).getResponse();
    }

    @Override
    public Response<SupportDTO> createSupport(SupportDTO supportDTO) {
        Support supportEntity = this.modelMapper.map(supportDTO, Support.class);

        //se asigna como trabajador al que tiene menos carga de complejidad
        supportEntity.setWorker(this.iWorkerRepository.findById(getBestWorker()).get());
        SupportDTO savedSupportDTO = this.modelMapper.map(iSupportRepository.save(supportEntity), SupportDTO.class);

        return new ResponseHandler<>(200, "Trabajador creado con éxito", "http://localhost:8081/worker/create_worker", savedSupportDTO).getResponse();
    }

    @Override
    public Response<SupportDTO> updateSupport(Integer supportSec, SupportDTO supportDTO) {
        return null;
    }

    @Override
    public Response<List<SupportDTO>> getSupportsByWorkerSec(Integer WorkerSec) {
        List<Support> supportList = this.iSupportRepository.findAll();
        if(supportList.isEmpty()){
            throw new BusinessRuleException("supports.not.found");
        }
        List<SupportDTO> supportListDTO = new ArrayList<>();

        for(Support support : supportList){
            if(Objects.equals(support.getWorker().getWorkerSec(), WorkerSec)){
                supportListDTO.add(this.modelMapper.map(support, SupportDTO.class));
            }
        }

        if(supportListDTO.isEmpty()){
            throw new BusinessRuleException("supports.not.found");
        }

        return new ResponseHandler<>(200, "Se han encontrado los soportes con exito", "http://localhost:8081/support/find_all_supports_by_worker/{workerSec}", supportListDTO).getResponse();
    }

    //metodo para determinar el trabajador con menos carga
    private Integer getBestWorker(){
        List<Worker> workers = this.iWorkerRepository.findAll();
        int minComplexity = Integer.MAX_VALUE; // Inicializamos con un valor muy grande para comparación
        Integer bestWorkerSec = null;

        for (Worker worker : workers) {
            Integer amountComplexity = 0;
            for (Support support : worker.getSupports()) {
                amountComplexity += support.getSupportComplexity();
            }

            // Si la carga acumulada de este trabajador es menor que la mínima registrada hasta ahora
            if (amountComplexity < minComplexity) {
                minComplexity = amountComplexity;
                bestWorkerSec = worker.getWorkerSec(); // Guardamos el secuencial del trabajador con la menor carga
            }
        }

        return bestWorkerSec; // Devolvemos el secuencial del trabajador con la menor carga acumulada
    }


}
