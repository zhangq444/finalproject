package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Position;
import org.springframework.stereotype.Repository;

/**
 * Created by grzha on 2018/7/30.
 */
@Repository
public interface PositionMapper {
    Position getPositionById(Position temp);

}
