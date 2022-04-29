package com.ranyk.www.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @CLASS_NAME: WorkFlowServiceImpl.java
 * @description: 工作流业务类
 * 
 * @author ranYk
 * @version V1.0
 */
@Service
public class WorkFlowServiceImpl {

    Map<String,Object> workFlowSubitVerifyServiceMap;

    public void berforeSubmitVerify(List<String> taskList, String workflowPass, boolean isNeedVerify){
        initWorkFlowSubitVerifyServicce();
    }

    private void initWorkFlowSubitVerifyServicce() {
        if(workFlowSubitVerifyServiceMap == null){
            workFlowSubitVerifyServiceMap = new HashMap<>(4);
        
        }
    }
    
}
