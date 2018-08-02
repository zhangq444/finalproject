package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.EmpToTr;
import org.springframework.stereotype.Repository;

/**
 * Created by grzha on 2018/8/2.
 */
@Repository
public interface EmpToTrMapper {
    void addNewEmpToTr(EmpToTr empToTr);

}
