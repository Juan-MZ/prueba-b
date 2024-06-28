package com.makrosoft.pruebab.support.service;

import com.makrosoft.pruebab.support.domain.SupportDTO;
import com.makrosoft.pruebab.utils.response.Response;

import java.util.List;

public interface ISupportService {
    /**
     * Service to get all supports
     *
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<List<SupportDTO>> findAllSupports();

    /**
     * Service to get a support
     *
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<SupportDTO> findSupportBySec(Integer supportSec);

    /**
     * Service to create a support
     *
     * @param supportDTO Object to do the save
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<SupportDTO> createSupport(SupportDTO supportDTO);

    /**
     * Service to update a support
     *
     * @param supportSec ID Object to do the search
     * @param supportDTO Object to do the update
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<SupportDTO> updateSupport(Integer supportSec, SupportDTO supportDTO);

    /**
     * Service to assign a support
     *
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<SupportDTO> assignSupport();

    /**
     * Service to get supports by worker
     *
     * @param WorkerSec ID Objects to do the search
     * @return {@link Response} Response object for the service, which contains
     * information about the outcome of the transaction.
     */
    public Response<List<SupportDTO>> getSupportsByWorkerSec(Integer WorkerSec);
}
