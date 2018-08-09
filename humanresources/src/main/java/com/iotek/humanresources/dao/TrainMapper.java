package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/8/1.
 */
@Repository
public interface TrainMapper {
    List<Train> getAllTrain();

    void addNewTrain(Train train);

    Train getTrainByThemeContentAddress(Train train);

    Train getTrainById(Train temp);

    void modifyTrainThemeContentBegintimeEndtimeAddressById(Train train);

    void modifyTrainReleasetimeStateById(Train train);

    void modifyTrainWithdrawTrain(Train train);

    List<Train> getTrainByEmpIdState(int id, int state);

    List<Train> getTrainByEmpIdStateByPage(int employeeInfoId, int state, int start, int end);

    List<Train> getAllTrainByPage(int start, int end);

}
