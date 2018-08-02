package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Train;

import java.util.List;

/**
 * Created by grzha on 2018/8/1.
 */
public interface TrainService {

    List<Train> getAllTrain();

    void addNewTrain(Train train);

    Train getTrainByThemeContentAddress(Train train);

    Train getTrainById(Train temp);

    void modifyTrainThemeContentBegintimeEndtimeAddressById(Train train);

    void modifyTrainReleasetimeStateById(Train train);

    void modifyTrainWithdrawTrain(Train train);

}
