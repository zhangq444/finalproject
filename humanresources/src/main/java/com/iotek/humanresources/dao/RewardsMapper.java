package com.iotek.humanresources.dao;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Repository
public interface RewardsMapper {
    void deleteRewardsLate(String explain, int empId);

    Rewards getTodayLateRewards(String explain, int empId);

    void addNewRewards(Rewards rewards1);

    Rewards getTodayEarlyRewards(String explain, int empId);

    List<Rewards> getAllRewardsByEMPID(Employee employee);

    List<Rewards> getAllRewardsByEMPIDByPage(int checkEmployeeAttendanceId, int start, int end);

    Rewards getRewardsById(Rewards temp);

    void modifyRewardsMoneyExplainById(Rewards rewards);

    void deleteRewardsById(Rewards temp);

}
