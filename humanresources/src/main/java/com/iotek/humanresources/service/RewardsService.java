package com.iotek.humanresources.service;

import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;

import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
public interface RewardsService {

    void deleteRewardsLate(String explain, int empId);

    Rewards getTodayLateRewards(String explain, int empId);

    void addNewRewards(Rewards rewards1);

    Rewards getTodayEarlyRewards(String explain, int empId);

    List<Rewards> getAllRewardsByEMPID(Employee employee);

    List<Rewards> getAllrewardsByPage(List<Rewards> rewardsListNowMonth, int currentPage, int pageSize);

    List<Rewards> getAllRewardsByEMPIDByPage(int checkEmployeeAttendanceId, int start, int end);

    Rewards getRewardsById(Rewards temp);

    void modifyRewardsMoneyExplainById(Rewards rewards);

    void deleteRewardsById(Rewards temp);

}
