package com.iotek.humanresources.service.impl;

import com.iotek.humanresources.dao.RewardsMapper;
import com.iotek.humanresources.model.Employee;
import com.iotek.humanresources.model.Rewards;
import com.iotek.humanresources.service.RewardsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzha on 2018/8/3.
 */
@Service
public class RewardsServiceImpl implements RewardsService {
    @Resource
    private RewardsMapper rewardsMapper;

    public void deleteRewardsLate(String explain, int empId) {
        rewardsMapper.deleteRewardsLate(explain,empId);
    }

    public Rewards getTodayLateRewards(String explain, int empId) {
        return rewardsMapper.getTodayLateRewards(explain,empId);
    }

    public void addNewRewards(Rewards rewards1) {
        rewardsMapper.addNewRewards(rewards1);
    }

    public Rewards getTodayEarlyRewards(String explain, int empId) {
        return rewardsMapper.getTodayEarlyRewards(explain,empId);
    }

    public List<Rewards> getAllRewardsByEMPID(Employee employee) {
        return rewardsMapper.getAllRewardsByEMPID(employee);
    }

    public List<Rewards> getAllrewardsByPage(List<Rewards> rewardsListNowMonth, int currentPage, int pageSize) {
        List<Rewards> rewardsListNowMonth1=new ArrayList<Rewards>();
        int max=(currentPage-1)*pageSize+pageSize>rewardsListNowMonth.size()?rewardsListNowMonth.size():(currentPage-1)*pageSize+pageSize;
        for(int i=(currentPage-1)*pageSize;i<max;i++){
            rewardsListNowMonth1.add(rewardsListNowMonth.get(i));
        }
        return rewardsListNowMonth1;
    }

    public List<Rewards> getAllRewardsByEMPIDByPage(int checkEmployeeAttendanceId, int start, int end) {
        return rewardsMapper.getAllRewardsByEMPIDByPage(checkEmployeeAttendanceId,start,end);
    }

    public Rewards getRewardsById(Rewards temp) {
        return rewardsMapper.getRewardsById(temp);
    }

    public void modifyRewardsMoneyExplainById(Rewards rewards) {
        rewardsMapper.modifyRewardsMoneyExplainById(rewards);
    }

    public void deleteRewardsById(Rewards temp) {
        rewardsMapper.deleteRewardsById(temp);
    }
}
