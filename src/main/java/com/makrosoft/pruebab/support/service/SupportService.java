package com.makrosoft.pruebab.support.service;

import com.makrosoft.pruebab.support.domain.SupportDTO;
import com.makrosoft.pruebab.support.model.Support;
import com.makrosoft.pruebab.support.respository.ISupportRepository;
import com.makrosoft.pruebab.utils.exception.BusinessRuleException;
import com.makrosoft.pruebab.utils.response.Response;
import com.makrosoft.pruebab.utils.response.handler.ResponseHandler;
import com.makrosoft.pruebab.worker.model.Worker;
import com.makrosoft.pruebab.worker.respository.IWorkerRepository;
import com.makrosoft.pruebab.worker.service.IWorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SupportService implements ISupportService{
    private final ISupportRepository iSupportRepository;
    private final IWorkerRepository iWorkerRepository;
    private final IWorkerService iWorkerService;
    private final ModelMapper modelMapper;

    public SupportService(ISupportRepository iSupportRepository, IWorkerRepository iWorkerRepository, IWorkerService iWorkerService, ModelMapper modelMapper) {
        this.iSupportRepository = iSupportRepository;
        this.iWorkerRepository = iWorkerRepository;
        this.iWorkerService = iWorkerService;
        this.modelMapper = modelMapper;
    }

    /**
     * @see ISupportService#findAllSupports()
     */
    @Override
    public Response<List<SupportDTO>> findAllSupports() {
        List<Support> supportList = this.iSupportRepository.findAll();
        if(supportList.isEmpty()){
            throw new BusinessRuleException("supports.not.found");
        }

        List<SupportDTO> supportListDTO = supportList.stream().map(support -> modelMapper.map(support, SupportDTO.class)).collect(Collectors.toList());

        return new ResponseHandler<>(200, "Se han encontrado los soportes con exito", "http://localhost:8081/support/find_all_supports", supportListDTO).getResponse();

    }

    /**
     * @see ISupportService#findSupportBySec(Integer)
     */
    @Override
    public Response<SupportDTO> findSupportBySec(Integer supportSec) {
        Optional<Support> supportFound = this.iSupportRepository.findById(supportSec);
        if(supportFound.isEmpty()){
            throw new BusinessRuleException("support.not.found");
        }

        SupportDTO supportDTO = modelMapper.map(supportFound.get(),SupportDTO.class);

        return new ResponseHandler<>(200, "Se ha encontrado el soporte con exito", "http://localhost:8081/support/find_support_by_sec/{supportSec}", supportDTO).getResponse();
    }

    /**
     * @see ISupportService#createSupport(SupportDTO)
     */
    @Override
    public Response<SupportDTO> createSupport(SupportDTO supportDTO) {
        Support supportEntity = this.modelMapper.map(supportDTO, Support.class);
        if((supportDTO.getSupportPriority()<1|| supportDTO.getSupportPriority()>5)||(supportDTO.getSupportWeight()<1||supportDTO.getSupportWeight()>5)){
            throw new BusinessRuleException("support.bad.format.exception");
        }
        SupportDTO savedSupportDTO = this.modelMapper.map(iSupportRepository.save(supportEntity), SupportDTO.class);

        return new ResponseHandler<>(200, "Soporte creado con éxito", "http://localhost:8081/worker/create_worker", savedSupportDTO).getResponse();
    }

    /**
     * @see ISupportService#updateSupport(Integer, SupportDTO)
     */
    @Override
    public Response<SupportDTO> updateSupport(Integer supportSec, SupportDTO supportDTO) {
        return null;
    }

    /**
     * @see ISupportService#assignSupport()
     */
    @Override
    public Response<SupportDTO> assignSupport() {
        Optional<Support> supportFound = this.iSupportRepository.findFirstBySupportAssignatedDateIsNullOrderBySupportPriorityDesc();
        if(supportFound.isEmpty()){
            throw new BusinessRuleException("support.not.found");
        }
        Support support = supportFound.get();
        Worker worker = this.iWorkerService.getWorkerLessBusy();
        support.setWorker(worker);
        support.setSupportAssignatedDate(LocalDate.now());

        SupportDTO supportDTO = this.modelMapper.map(this.iSupportRepository.save(support), SupportDTO.class);

        return new ResponseHandler<>(200, "Se ha asignado el soporte de mayor prioridad con éxito.", "http://localhost:8081/support/assign_support", supportDTO).getResponse();
    }

    /**
     * @see ISupportService#getSupportsByWorkerSec(Integer)
     */
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

}
