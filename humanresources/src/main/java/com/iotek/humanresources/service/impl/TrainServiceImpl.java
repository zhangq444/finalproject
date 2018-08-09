package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.TrainMapper;
import com.iotek.humanresources.model.Train;
import com.iotek.humanresources.service.TrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by grzha on 2018/8/1.
 */
@Service
public class TrainServiceImpl implements TrainService {
    @Resource
    private TrainMapper trainMapper;

    public List<Train> getAllTrain() {
        return trainMapper.getAllTrain();
    }

    public void addNewTrain(Train train) {
        trainMapper.addNewTrain(train);
    }

    public Train getTrainByThemeContentAddress(Train train) {
        return trainMapper.getTrainByThemeContentAddress(train);
    }

    public Train getTrainById(Train temp) {
        return trainMapper.getTrainById(temp);
    }

    public void modifyTrainThemeContentBegintimeEndtimeAddressById(Train train) {
        trainMapper.modifyTrainThemeContentBegintimeEndtimeAddressById(train);
    }

    public void modifyTrainReleasetimeStateById(Train train) {
        trainMapper.modifyTrainReleasetimeStateById(train);
    }

    public void modifyTrainWithdrawTrain(Train train) {
        trainMapper. modifyTrainWithdrawTrain(train);
    }

    public List<Train> getTrainByEmpIdState(int id, int state) {
        return trainMapper.getTrainByEmpIdState(id,state);
    }

    public List<Train> getTrainByEmpIdStateByPage(int employeeInfoId, int state, int start, int end) {
        return trainMapper.getTrainByEmpIdStateByPage(employeeInfoId,state,start,end);
    }

    public List<Train> getAllTrainByPage(int start, int end) {
        return trainMapper.getAllTrainByPage(start,end);
    }

}
