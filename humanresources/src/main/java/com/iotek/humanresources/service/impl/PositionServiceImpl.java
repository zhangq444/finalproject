package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.PositionMapper;
import com.iotek.humanresources.model.Position;
import com.iotek.humanresources.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by grzha on 2018/7/30.
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;

    public Position getPositionById(Position temp) {
        return positionMapper.getPositionById(temp);
    }

    public void addNewPosition(Position position) {
        positionMapper.addNewPosition(position);
    }

    public Position getPositionByName(Position position) {
        return positionMapper.getPositionByName(position);
    }

    public void modifyPositionNameSalaryById(Position position) {
        positionMapper.modifyPositionNameSalaryById(position);
    }

    public void deletePositionById(Position position) {
        positionMapper.deletePositionById(position);
    }
}
