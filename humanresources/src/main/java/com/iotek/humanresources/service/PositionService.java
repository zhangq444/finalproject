package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Position;

/**
 * Created by grzha on 2018/7/30.
 */
public interface PositionService {
    Position getPositionById(Position temp);

    void addNewPosition(Position position);

    Position getPositionByName(Position position);

    void modifyPositionNameSalaryById(Position position);

    void deletePositionById(Position position);

    void deletePositionByDepId(int selectDep);

}
